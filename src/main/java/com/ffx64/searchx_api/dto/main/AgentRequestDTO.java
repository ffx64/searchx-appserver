package com.ffx64.searchx_api.dto.main;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AgentRequestDTO(
    String name,
    
    @JsonProperty("user_id")
    @NotNull(message="User ID cannot be null")
    UUID userId,

    @JsonProperty("agent_type")
    @NotBlank(message="Agent type cannot be empty")
    String agentType,

    @JsonProperty("agent_status")
    @NotBlank(message="Agent status cannot be empty")
    String agentStatus,

    @JsonProperty("auth_key")
    @NotBlank(message="Auth key cannot be empty")
    String authKey,

    @NotBlank(message="Platform cannot be empty")
    String platform,

    @JsonProperty("collection_interval")
    @NotNull(message="Collection interval cannot be null")
    Integer collectionInterval,

    List<String> tags
) {
    
}
