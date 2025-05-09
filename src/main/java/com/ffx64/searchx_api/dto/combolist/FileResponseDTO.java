package com.ffx64.searchx_api.dto.combolist;

import java.time.OffsetDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponseDTO {
    private Long id;
    private String agentKey;
    private String name;
    private Long size;
    private String hash;
    private OffsetDateTime createdAt;
    private Date processedAt;
    private Integer status;
    private String source;
    private String type;
    private String description;
    private Integer processedEntriesCount;
}
