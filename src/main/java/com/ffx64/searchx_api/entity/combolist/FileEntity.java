package com.ffx64.searchx_api.entity.combolist;

import java.time.OffsetDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="agent_key", nullable=false, length=64)
    private String agentKey;

    @Column(name="name", nullable=false, length=255)
    private String name;

    @Column(name="size", nullable=false)
    private Long size;

    @Column(name="hash", nullable=false, length=64, unique=true)
    private String hash;

    @Column(name="created_at", columnDefinition="TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime createdAt;

    @Column(name="processed_at")
    @Temporal(TemporalType.DATE)
    private Date processedAt;

    @Column(name="status", nullable=false)
    private Integer status = 0; // Default status is 'pending'

    @Column(name="source", length=255)
    private String source;

    @Column(name="type", length=20)
    private String type;

    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @Column(name="processed_entries_count", nullable=false)
    private Integer processedEntriesCount = 0;

    @PrePersist
    protected void onCreate() {
        createdAt = OffsetDateTime.now();
    }
}
