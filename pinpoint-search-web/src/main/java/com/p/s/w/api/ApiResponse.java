package com.p.s.w.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Api 统一 Response
 * @param <T> 数据
 * @author clufeng
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties
public class ApiResponse<T> {

    private int code;

    private String message;

    private T data;

    public static <T> ApiResponse<T> of(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static ApiResponse<?> of(int code, String message) {
        return of(code, message, null);
    }

    public static ApiResponse<?> of(ApiStatus apiStatus) {
        return of(apiStatus.getCode(), apiStatus.getMessage(), null);
    }

    public static <T> ApiResponse<T> of(int code, T data) {
        return of(code, null, data);
    }

    public static <T> ApiResponse<T> of(ApiStatus apiStatus, T data) {
        return of(apiStatus.getCode(), apiStatus.getMessage(), data);
    }

}
