package com.ffx64.searchx_api.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("invalid username or password.");
    }
}
