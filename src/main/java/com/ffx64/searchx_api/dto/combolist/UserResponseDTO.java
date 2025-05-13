package com.ffx64.searchx_api.dto.combolist;

import java.time.OffsetDateTime;

public record UserResponseDTO(
    Long id,
    FileResponseDTO file,
    String username,
    String password,
    OffsetDateTime createdAt
) {}
