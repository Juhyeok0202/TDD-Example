package com.example.ttd.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/*
[MembershipErrorResult를 작성한 이유]
추후에 MembershipException이 throw되었을 때 RestControllerAdvice를 통해
MembershipErrorResult의 HttpStatus와 message를 반환하기 위함이다.
(TDD를 하면 이러한 고민을 하게 되고, 이느 좋은 설계로 이어지게 될 수 있다.)
 */
@Getter
@RequiredArgsConstructor
public enum MembershipErrorResult {

    DUPLICATED_MEMBERSHIP_REGISTER(HttpStatus.BAD_REQUEST, "Duplicated Membership Register Request"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
