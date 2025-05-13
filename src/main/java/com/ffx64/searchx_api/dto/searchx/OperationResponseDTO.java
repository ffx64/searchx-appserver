package com.ffx64.searchx_api.dto.searchx;

import java.time.OffsetDateTime;

public record OperationResponseDTO(
    Long id,
    String name,
    String description,
    OffsetDateTime startDate,
    OffsetDateTime endDate
) {}