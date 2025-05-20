package com.ffx64.searchx_api.exception;

public class AgentAndPlatformExistsException extends RuntimeException {
    public AgentAndPlatformExistsException() {
        super("the chosen name and platform is already in use. please select a different one.");
    }
}