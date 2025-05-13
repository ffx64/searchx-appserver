package com.ffx64.searchx_api.dto.combolist;

import java.time.OffsetDateTime;

public record UrlResponseDTO(
    Long id,
    UserResponseDTO user,
    String url,
    String fileLine,
    OffsetDateTime createdAt
) {}
