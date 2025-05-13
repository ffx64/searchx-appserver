package com.ffx64.searchx_api.dto.searchx;

import java.time.OffsetDateTime;

public record UserResponseDTO(
    Long id,
    String username,
    int agentsCount,
    OffsetDateTime createdAt
) {}
