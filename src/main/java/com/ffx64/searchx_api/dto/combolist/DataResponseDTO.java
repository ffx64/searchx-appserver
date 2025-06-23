package com.ffx64.searchx_api.dto.combolist;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DataResponseDTO(
    UUID id,
    String email,
    String username,
    String password,
    String domain,

    @JsonProperty("metadata_id")
    UUID metadataId
) {
    
}
