package com.example.portfolio.Enum;

public enum ErrorCode {
    USAGE(1, "Usage error"),
    RESOURCE_NOT_FOUND(2, "Resource not found"),
    DATA_LOAD_ERROR(3, "Data load error"),
    ERROR(4, "Runtime exception");

    private int code;
    private String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
