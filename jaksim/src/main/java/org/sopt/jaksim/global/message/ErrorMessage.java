package org.sopt.jaksim.global.message;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ErrorMessage {
    /**
     * 400 Bad Request
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "e4000", "잘못된 요청입니다."),
    INVALID_PLATFORM_TYPE(HttpStatus.BAD_REQUEST, "e4001", "유효하지 않은 플랫폼 타입입니다."),

    /**
     * 401 Unauthorized
     */
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED, "e4010", "사용자의 로그인 검증을 실패했습니다."),
    PASSWORD_NOT_MATCHED_EXCEPTION(HttpStatus.UNAUTHORIZED, "e4010", "해당 유저의 비밀번호가 일치하지 않습니다."),
    ;

    /**
     * 403 Forbidden
     */

    /**
     * 404 Not Found
     */

    /**
     * 405 Method Not Allowed
     */

    /**
     * 409 Conflict
     */

    /**
     * 500 Internal Server Error
     */

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
   }
