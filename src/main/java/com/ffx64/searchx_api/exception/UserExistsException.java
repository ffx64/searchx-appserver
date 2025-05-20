package com.ffx64.searchx_api.exception;

public class UserExistsException extends RuntimeException {
    public UserExistsException() {
        super("the chosen username is already in use. please select a different one.");
    }
}