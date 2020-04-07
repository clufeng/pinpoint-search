package com.p.s.w.api;

/**
 * Api 状态
 */
public enum ApiStatus {

    OK(200, "OK"),
    NOT_FOUND(404, "NOT_FOUND"),
    UNAUTHORIZED(401, "Unauthorized"),
    SYS_ERROR(500, "SYS_ERROR");

    private final int code;

    private final String message;

    ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
