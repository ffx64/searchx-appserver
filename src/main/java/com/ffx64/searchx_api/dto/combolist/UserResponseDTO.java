package com.ffx64.searchx_api.dto.combolist;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private FileResponseDTO file;
    private String username;
    private String password;
    private OffsetDateTime createdAt;
}
