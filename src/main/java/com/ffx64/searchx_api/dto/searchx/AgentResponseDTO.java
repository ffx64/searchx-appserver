package com.ffx64.searchx_api.dto.searchx;

import lombok.Data;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentResponseDTO {
    private Long id;
    private String name;
    private String key;
    private String reason;
    private boolean active;
    private OffsetDateTime createdAt;
}
