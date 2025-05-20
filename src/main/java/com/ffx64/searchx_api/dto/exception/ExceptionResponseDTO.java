package com.ffx64.searchx_api.dto.exception;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExceptionResponseDTO(
    String error,
    String message,
    
    @JsonProperty("status_code")
    Integer statusCode,
 
    OffsetDateTime timestamp
) {

}