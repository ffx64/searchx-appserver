package com.ffx64.searchx_api.dto.combolist;

import lombok.Data;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String url;
    private String fileLine;
    private OffsetDateTime createdAt;
}
