package org.sopt.jaksim.global.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum SuccessMessage {
    /**
     * 200 Ok
     */
    USER_SIGN_UP_SUCCESS(HttpStatus.OK, "s2000", "회원가입이 완료되었습니다."),
    USER_SIGN_IN_SUCCESS(HttpStatus.OK, "s2001", "로그인이 완료되었습니다."),
    USER_TOKEN_REISSUE_SUCCESS(HttpStatus.OK, "s2002", "토큰 재발급이 완료되었습니다."),
    ;

    /**
     * 201 Created
     */

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
