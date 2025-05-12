package com.ffx64.searchx_api.repository.searchx;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.searchx.OperationEntity;

@Repository("searchxOperationRepository")
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    Optional<OperationEntity> findByName(String name);
    Optional<OperationEntity> findByDescription(String description);
}
