package com.company.dto;

public class BaseResult<T> {
    private boolean success;
    private T result;
    private String message;

    public BaseResult(boolean success, T result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
