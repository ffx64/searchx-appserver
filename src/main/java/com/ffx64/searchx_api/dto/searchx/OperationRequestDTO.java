package com.ffx64.searchx_api.dto.searchx;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record OperationRequestDTO(
    @NotBlank
    @Length(min=5, max=30)
    String name,

    @NotBlank
    @Length(min=10, max=255)
    String description
) {}