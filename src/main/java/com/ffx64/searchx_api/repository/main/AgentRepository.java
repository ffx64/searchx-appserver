package com.ffx64.searchx_api.repository.main;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ffx64.searchx_api.entity.main.AgentEntity;
import com.ffx64.searchx_api.entity.main.UserEntity;

@Repository("MainAgentRepository")
public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    List<AgentEntity> findByAgentType(String agentType);

    List<AgentEntity> findByAgentStatus(String agentStatus);

    List<AgentEntity> findByUserEntity(UserEntity userEntity);

    List<AgentEntity> findByPlatform(String platform);

    List<AgentEntity> findByCollectionInterval(Integer collectionInterval);

    List<AgentEntity> findByLastActivityAtAfter(LocalDateTime lastActivityAfter);

    boolean existsByNameAndPlatform(String name, String platform);
}
