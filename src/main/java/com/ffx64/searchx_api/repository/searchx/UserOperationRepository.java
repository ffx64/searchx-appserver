package com.ffx64.searchx_api.repository.searchx;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.searchx.UserOperationEntity;

@Repository("searchxUserOperationRepository")
public interface UserOperationRepository extends JpaRepository<UserOperationEntity, Long> {
    Optional<UserOperationEntity> findByUserId(Long userId);
    Optional<UserOperationEntity> findByOperationId(Long operationId);
    Optional<UserOperationEntity> findByRole(Integer role);
}
