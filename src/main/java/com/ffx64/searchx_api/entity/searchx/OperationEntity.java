package com.ffx64.searchx_api.entity.searchx;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="operations")
public class OperationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, columnDefinition="TEXT")
    private String name;

    @Column(name="description", nullable=false, columnDefinition="TEXT")
    private String description;

    @Column(name="start_date", nullable=false, columnDefinition="TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime startDate;

    @Column(name="end_date", columnDefinition="TIMESTAMPTZ")
    private OffsetDateTime endDate;

    @PrePersist
    protected void onStartDate() {
        startDate = OffsetDateTime.now();
    }
}
