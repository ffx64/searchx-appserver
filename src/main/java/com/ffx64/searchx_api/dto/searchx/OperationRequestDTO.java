package com.ffx64.searchx_api.dto.searchx;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OperationRequestDTO {

    @NotBlank
    @Length(min=5, max=30)
    private String name;

    @NotBlank
    @Length(min=10, max=255)
    private String description;
}
