package com.ffx64.searchx_api.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("the specified user could not be located. please verify the user ID and try again.");
    }
}