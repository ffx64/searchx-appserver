package com.ffx64.searchx_api.dto.searchx;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentRequestDTO {
    private Long id;
    private String name;
    private String reason;
}
