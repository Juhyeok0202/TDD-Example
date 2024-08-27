package com.example.ttd.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
/*
[RuntimeException(언체크 예외)로 둔 이유]
예외 복구 가능성이 없으므로 예외 처리를 개발자가 강제할 필요가 없으며,
트랜잭션 내에서 언체크 예외 만이 자동으로 롤백되기 때문이되고 체크 예외는 롤백되지 않는다.
(물론 옵션으로 체크 예외도 롤백되도록 변경할 수 있긴..하다..)
 */
public class MembershipException extends RuntimeException {

    private final MembershipErrorResult errorResult;
}
