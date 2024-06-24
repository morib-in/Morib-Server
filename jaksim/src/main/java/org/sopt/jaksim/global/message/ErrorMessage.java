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
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "e4010", "리소스 접근 권한이 없습니다."),
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED, "e4011", "사용자의 로그인 검증을 실패했습니다."),
    PASSWORD_NOT_MATCHED_EXCEPTION(HttpStatus.UNAUTHORIZED, "e4012", "해당 유저의 비밀번호가 일치하지 않습니다."),

    /**
     * 403 Forbidden
     */
    FORBIDDEN(HttpStatus.FORBIDDEN, "e4030", "리소스 접근 권한이 없습니다."),

    /**
     * 404 Not Found
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "e4040", "대상을 찾을 수 없습니다."),

    /**
     * 405 Method Not Allowed
     */

    /**
     * 409 Conflict
     */

    /**
     * 500 Internal Server Error
     */
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


   }
