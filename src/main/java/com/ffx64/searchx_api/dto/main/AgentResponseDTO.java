package com.ffx64.searchx_api.dto.main;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AgentResponseDTO(
    UUID id,
    
    String name,

    @JsonProperty("user_id")
    UUID userId,
    
    @JsonProperty("agent_type")
    String agentType,
    
    @JsonProperty("agent_status")
    String agentStatus,
    
    @JsonProperty("auth_key")
    String authKey,

    String platform,

    List<String> tags,

    @JsonProperty("data_processed")
    Integer dataProcessed,

    @JsonProperty("last_activity_at")
    OffsetDateTime lastActivityAt,
    
    @JsonProperty("created_at")
    OffsetDateTime createdAt,

    @JsonProperty("updated_at")
    OffsetDateTime updatedAt,

    @JsonProperty("collection_interval")
    Integer collectionInterval
) {
    
}