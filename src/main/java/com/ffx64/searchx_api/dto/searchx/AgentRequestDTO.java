package com.ffx64.searchx_api.dto.searchx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentRequestDTO {
    private Long id;
    private String name;
    private String reason;
}
