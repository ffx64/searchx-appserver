package com.ffx64.searchx_api.exception;

public class MetadataNotFoundException extends RuntimeException {
    public MetadataNotFoundException() {
        super("the specified metadata could not be located. please verify the ID and try again.");
    }
}