package org.sopt.jaksim.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.exception.ForbiddenException;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.exception.UnauthorizedException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(">>> handle: MethodArgumentNotValidException ", e);
        return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<BaseResponse<?>> handleNotFoundException(NotFoundException e) {
        log.error(">>> handle: NotFoundException ", e);
        return ApiResponseUtil.failure(ErrorMessage.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<BaseResponse<?>> handleForbiddenException(ForbiddenException e) {
        log.error(">>> handle: ForbiddenException ", e);
        return ApiResponseUtil.failure(ErrorMessage.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<BaseResponse<?>> handleUnauthorizedException(UnauthorizedException e) {
        log.error(">>> handle: UnauthorizedException ", e);
        return ApiResponseUtil.failure(ErrorMessage.UNAUTHORIZED);
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<BaseResponse<?>> handleIOException(IOException e) {
        log.error(">>> handle: IOException ", e);
        return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
    }

    @ExceptionHandler(OAuthException.class)
    protected ResponseEntity<BaseResponse<?>> handleOAuthException(OAuthException e) {
        log.error(">>> handle: OAuthException ", e);
        return ApiResponseUtil.failure(ErrorMessage.INVALID_GRANT_BY_OAUTH);
    }

    @ExceptionHandler(DateTimeParseException.class)
    protected ResponseEntity<BaseResponse<?>> handleDateTimeParseException(DateTimeParseException e) {
        log.error(">>> handle: DateTimeParseException ", e);
        return ApiResponseUtil.failure(ErrorMessage.INVALID_GRANT_BY_OAUTH);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<BaseResponse<?>> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error(">>> handle: HttpRequestMethodNotSupportedException ", e);
        return ApiResponseUtil.failure(ErrorMessage.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<BaseResponse<?>> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.error(">>> handle: MethodArgumentTypeMismatchException ", e);
        return ApiResponseUtil.failure(ErrorMessage.BAD_REQUEST);
    }


}
