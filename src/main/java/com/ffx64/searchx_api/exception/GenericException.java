package com.ffx64.searchx_api.exception;

public class GenericException extends RuntimeException {
    public GenericException() {
        super("an unexpected error occurred. please try again later or contact support if the problem persists.");
    }

    public GenericException(String message) {
        super(message);
    }
}