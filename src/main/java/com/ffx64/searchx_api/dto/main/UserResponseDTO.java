package com.ffx64.searchx_api.dto.main;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;

public record UserResponseDTO(
    Long id,
    String username,
    
    @JsonProperty("full_name")
    String fullName,
    
    @Email
    String email,

    String role,

    @JsonProperty("is_active")
    Boolean isActive,

    @JsonProperty("created_at")
    OffsetDateTime createdAt,

    @JsonProperty("last_login")
    OffsetDateTime lastLogin
) {
    
}
