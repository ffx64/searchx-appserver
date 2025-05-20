package com.ffx64.searchx_api.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("access denied.");
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}