package com.ffx64.searchx_api.dto.main;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AgentRequestDTO(
    String name,

    @JsonProperty("agent_type")
    @NotBlank(message="Agent type cannot be empty")
    String agentType,

    @JsonProperty("agent_status")
    @NotBlank(message="Agent status cannot be empty")
    String agentStatus,

    @NotBlank(message="Platform cannot be empty")
    String platform,

    @JsonProperty("collection_interval")
    @NotNull(message="Collection interval cannot be null")
    Integer collectionInterval,

    List<String> tags
) {
    
}
