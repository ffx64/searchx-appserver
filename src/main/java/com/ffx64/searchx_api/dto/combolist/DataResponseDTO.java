package com.ffx64.searchx_api.dto.combolist;

import java.util.UUID;

public record DataResponseDTO(
    UUID id,
    String email,
    String username,
    String password,
    String domain
) {
    
}
