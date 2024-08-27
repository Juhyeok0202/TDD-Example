package com.example.ttd.service;

import com.example.ttd.domain.Membership;
import com.example.ttd.domain.MembershipType;
import com.example.ttd.dto.MembershipResponse;
import com.example.ttd.exception.MembershipErrorResult;
import com.example.ttd.exception.MembershipException;
import com.example.ttd.repository.MembershipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
서비스 계층은 데이터베이스에 데이터를 처리하는 레포지토리 계층을 모킹하기 위해
MockitoExtension에서 실행되도록 한다.

Mockito를 이용한 단위테스트에 대한 공부가 부족하면 따로 공부하자.
(https://mangkyu.tistory.com/145?category=761302)
 */
@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    // @Mock이 붙은 목객체를 @InjectMocks이 붙은 객체에 주입시킬 수 있다.
    // 실무에서는 @InjectMocks(Service) @Mock(DAO) 이런식으로 Service 체스트 목객체에 DAO 목객체를 주입시켜 사용한다.
    @Mock
    private MembershipRepository membershipRepository;
    @InjectMocks
    private MembershipService target;

    /*
    멤버십 등록에 대한 테스트 코드를 작성해야 하는데, 사용자Id와 멤버십 타입으로 이미 멤버십이
    존재하여 {실패하는 테스트 코드부터 작성한다.} 즉, membershipRepository의 findByUserIdAndMembershipType를
    호출했을 때 결과가 null이 아니여야 하는 것이다.
     */

    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10_000;

    @Test
    public void 멤버십등록실패_이미존재함() {
        //given

        //target.addMembership(...) 내부에서 findByUserIdAndMembershipType(...)호출될 때,
        //DB에 존재하는 유저가 없어도 Membership을 반환하도록 해준다.
        //따라서, 서비스로직에서 null값이 아닌 Membership 객체가 반환된다.(DB에 없음에도)
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);

        //when
        final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, membershipType, point));

        //then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
    }

    @Test
    public void 멤버십등록성공() {
        //given
        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
        doReturn(membership()).when(membershipRepository).save(any(Membership.class)); //🤔

        //when
        final MembershipResponse result = target.addMembership(userId, membershipType, point);

        //then
        assertThat(result.id()).isNotNull();
        assertThat(result.membershipType()).isEqualTo(MembershipType.NAVER);

        //verify (membershipRepository를 통해 메소드가 호출되었는지를 검증하는 verify단계)
        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
        verify(membershipRepository, times(1)).save(any(Membership.class));
    }

    private Membership membership() {
        return Membership.builder()
                .id(-1L)
                .userId(userId)
                .membershipType(MembershipType.NAVER)
                .point(point)
                .build();
    }
}
