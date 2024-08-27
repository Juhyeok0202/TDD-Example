package com.example.ttd.service;

import com.example.ttd.domain.Membership;
import com.example.ttd.domain.MembershipType;
import com.example.ttd.exception.MembershipErrorResult;
import com.example.ttd.exception.MembershipException;
import com.example.ttd.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Membership addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if (result != null) {
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                .userId(userId)
                .point(point)
                .membershipType(membershipType)
                .build();

        return membershipRepository.save(membership);
    }
}
