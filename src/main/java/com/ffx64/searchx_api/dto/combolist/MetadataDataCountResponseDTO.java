package com.ffx64.searchx_api.dto.combolist;

import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

public record MetadataDataCountResponseDTO(
    UUID id,
    String source,
    OffsetTime collectedAt,
    List<String> tags,
    String notes,
    long total
) {}