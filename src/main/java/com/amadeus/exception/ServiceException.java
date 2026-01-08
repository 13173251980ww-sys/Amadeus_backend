package com.amadeus.exception;

public class ServiceException extends RuntimeException {
    private Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return super.getMessage();
    }
}
