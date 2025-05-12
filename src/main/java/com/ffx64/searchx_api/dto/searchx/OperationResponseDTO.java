package com.ffx64.searchx_api.dto.searchx;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationResponseDTO {
    private Long id;
    private String name;
    private String description;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
}
