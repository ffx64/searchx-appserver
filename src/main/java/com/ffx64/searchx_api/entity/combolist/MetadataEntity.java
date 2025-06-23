package com.ffx64.searchx_api.entity.combolist;

import java.time.OffsetTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="combolist_metadata")
public class MetadataEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name="source", nullable=false)
    private String source;

    @Column(name="collected_at", columnDefinition="TIMESTAMPTZ")
    private OffsetTime collectedAt;

    private List<String> tags;

    private String notes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public OffsetTime getCollectedAt() {
        return collectedAt;
    }

    public void setCollectedAt(OffsetTime collectedAt) {
        this.collectedAt = collectedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
