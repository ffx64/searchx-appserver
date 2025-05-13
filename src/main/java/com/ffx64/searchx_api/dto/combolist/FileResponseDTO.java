package com.ffx64.searchx_api.dto.combolist;

import java.time.OffsetDateTime;
import java.util.Date;

public record FileResponseDTO(
    Long id,
    String agentKey,
    String name,
    Long size,
    String hash,
    OffsetDateTime createdAt,
    Date processedAt,
    Integer status,
    String source,
    String type,
    String description,
    Integer processedEntriesCount
) {}