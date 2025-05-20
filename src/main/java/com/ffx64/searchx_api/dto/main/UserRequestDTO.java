package com.ffx64.searchx_api.dto.main;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequestDTO(
    @NotBlank(message="Username cannot be empty")
    @Size(min=3, max=50, message="Username must be between 3 and 50 characters")
    String username,

    @JsonProperty("full_name")
    @Size(max=100, message="Full name cannot exceed 100 characters")
    String fullName,

    @Email(message="Invalid email format")
    @NotBlank(message="Email cannot be empty")
    String email,

    @NotBlank(message="Role cannot be empty")
    String role,

    @NotNull(message="Password cannot be null")
    @Size(min=8, message="Password must be at least 8 characters long")
    String password
) {
    
}
