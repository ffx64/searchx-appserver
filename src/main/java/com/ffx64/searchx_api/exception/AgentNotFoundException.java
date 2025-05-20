package com.ffx64.searchx_api.exception;

public class AgentNotFoundException extends RuntimeException {
    public AgentNotFoundException() {
        super("the specified agent could not be located. please verify the agent ID and try again.");
    }
}