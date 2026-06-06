package com.example.fincard.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "존재하지 않는 사용자입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, 409, "이미 사용 중인 이메일입니다."),
    DUPLICATE_PHONE(HttpStatus.CONFLICT, 409, "이미 사용 중인 전화번호입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, 400, "비밀번호가 일치하지 않습니다."),

    // Auth
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, 401, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, 401, "만료된 토큰입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, 401, "비밀번호가 올바르지 않습니다."),

    // Quiz
    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, 404, "퀴즈를 찾을 수 없습니다."),

    // Common
    INVALID_INPUT(HttpStatus.BAD_REQUEST, 400, "입력값이 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}