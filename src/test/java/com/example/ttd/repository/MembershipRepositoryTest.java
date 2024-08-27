package com.example.ttd.repository;

import com.example.ttd.domain.Membership;
import com.example.ttd.domain.MembershipType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MembershipRepositoryTest {

    /*
    Repository계층은 통합 테스트이므로 먼저 MemberRepo.라는 빈이
    잘 띄워지는지부터 테스트 해본다.
     */
    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    public void MembershipRepository가NULL이아님() {
        assertThat(membershipRepository).isNotNull();
    }

    @Test
    public void 멤버십등록() {
        //given
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10_000)
                .build();

        //when
        final Membership result = membershipRepository.save(membership);

        //then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo("userId");
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(result.getPoint()).isEqualTo(10_000);
    }

    @Test
    public void 멤버십이존재하는지테스트() {
        //given
        final Membership membership = Membership.builder()
                .userId("userId")
                .membershipType(MembershipType.NAVER)
                .point(10_000)
                .build();

        //when
        membershipRepository.save(membership);
        final Membership findResult = membershipRepository.findByUserIdAndMembershipType("userId", MembershipType.NAVER);

        //then
        assertThat(findResult).isNotNull();
        assertThat(findResult.getId()).isNotNull();
        assertThat(findResult.getUserId()).isEqualTo("userId");
        assertThat(findResult.getMembershipType()).isEqualTo(MembershipType.NAVER);
        assertThat(findResult.getPoint()).isEqualTo(10_000);
    }
}
