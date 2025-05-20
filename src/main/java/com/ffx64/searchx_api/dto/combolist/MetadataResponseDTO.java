package com.ffx64.searchx_api.dto.combolist;

import java.time.OffsetTime;
import java.util.List;

public record MetadataResponseDTO (
    Long id,

    String source,

    OffsetTime collectedAt,
    
    List<String> tags,

    String notes
) {

}
