package com.example.ttd.dto;

import com.example.ttd.domain.MembershipType;
import lombok.Builder;

@Builder
public record MembershipResponse(
        Long id,
        MembershipType membershipType
        )
{}
