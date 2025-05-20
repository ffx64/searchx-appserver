package com.ffx64.searchx_api.dto.combolist;

public record DataMResponseDTO(

    Long id,

    String email,

    String username,

    String password,

    String domain,

    MetadataResponseDTO metadata
) {
    
}
