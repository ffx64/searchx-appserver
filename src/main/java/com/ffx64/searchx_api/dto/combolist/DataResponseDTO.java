package com.ffx64.searchx_api.dto.combolist;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DataResponseDTO(

    Long id,

    String email,

    String username,

    String password,

    String domain,

    @JsonProperty("metadata_id")
    Long metadataId
) {
    
}
