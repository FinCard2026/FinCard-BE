package com.example.fincard.global.response;

import lombok.Getter;

@Getter
public class BaseResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;

    private BaseResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse<>(true, message, data);
    }

    public static <T> BaseResponse<T> failure(String message) {
        return new BaseResponse<>(false, message, null);
    }
}