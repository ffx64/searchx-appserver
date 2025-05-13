package com.ffx64.searchx_api.dto.searchx;

import java.time.OffsetDateTime;

public record AgentResponseDTO(
    Long id,
    String name,
    String key,
    String reason,
    boolean active,
    OffsetDateTime createdAt
) {}