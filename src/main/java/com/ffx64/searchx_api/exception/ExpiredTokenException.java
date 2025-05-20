package com.ffx64.searchx_api.exception;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException() {
        super("the provided token has expired. please authenticate again to obtain a new one.");
    }
}
