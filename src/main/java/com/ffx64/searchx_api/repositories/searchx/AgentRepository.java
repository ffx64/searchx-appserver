package com.ffx64.searchx_api.repositories.searchx;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entities.searchx.AgentEntity;

@Repository("searchxAgentRepository")
public interface AgentRepository extends JpaRepository<AgentEntity, Long> {
    List<AgentEntity> findByUserId(Long userId);
    Optional<AgentEntity> findByKey(String key);
}
