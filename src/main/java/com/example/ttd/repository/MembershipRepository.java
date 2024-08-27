package com.example.ttd.repository;

import com.example.ttd.domain.Membership;
import com.example.ttd.domain.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Membership findByUserIdAndMembershipType(final String userId, final MembershipType membershipType);
}
