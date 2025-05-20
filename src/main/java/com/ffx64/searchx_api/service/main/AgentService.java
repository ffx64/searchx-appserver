package com.ffx64.searchx_api.service.main;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffx64.searchx_api.dto.main.AgentRequestDTO;
import com.ffx64.searchx_api.dto.main.AgentResponseDTO;
import com.ffx64.searchx_api.entity.main.AgentEntity;
import com.ffx64.searchx_api.entity.main.UserEntity;
import com.ffx64.searchx_api.exception.AgentAndPlatformExistsException;
import com.ffx64.searchx_api.exception.AgentNotFoundException;
import com.ffx64.searchx_api.infra.security.TokenService;
import com.ffx64.searchx_api.repository.main.AgentRepository;

@Service("MainAgentService")
public class AgentService {
    
    @Autowired
    AgentRepository repository;

    @Autowired
    TokenService tokenService;

    @Transactional
    public AgentResponseDTO create(AgentRequestDTO dto) {
        AgentEntity agent = new AgentEntity();

        boolean alreadyExists = repository.existsByNameAndPlatform(dto.name(), dto.platform());
        if (alreadyExists) {
            throw new AgentAndPlatformExistsException();
        }

        UserEntity userx = tokenService.getAuthenticatedUser();

        String hashed = new BCryptPasswordEncoder().encode(dto.name() + ":" + dto.platform() + ":" + OffsetTime.now().getNano());
        
        agent.setName(dto.name());
        agent.setUserEntity(userx);
        agent.setAgentStatus(dto.agentStatus());
        agent.setAgentType(dto.agentType());
        agent.setCollectionInterval(dto.collectionInterval());
        agent.setAuthKey(hashed);
        agent.setPlatform(dto.platform());
        agent.setLastUpdatedBy(userx);
        agent.setTags(dto.tags());
        agent.setUpdatedAt(OffsetDateTime.now());

        AgentEntity agentSaved = repository.save(agent);

        return new AgentResponseDTO(
            agentSaved.getId(),
            agentSaved.getName(),
            agentSaved.getUserEntity().getId(),
            agentSaved.getAgentType(),
            agentSaved.getAgentStatus(),
            agentSaved.getAuthKey(),
            agentSaved.getPlatform(),
            agentSaved.getTags(),
            agentSaved.getDataProcessed(),
            agentSaved.getLastActivityAt(),
            agentSaved.getCreatedAt(),
            agentSaved.getUpdatedAt(),
            agentSaved.getCollectionInterval()
        );
    }

    @Transactional
    public AgentResponseDTO update(Long id, AgentRequestDTO dto) {
        UserEntity authenticatedUser = tokenService.getAuthenticatedUser();

        AgentEntity agent = repository.findById(id).orElseThrow(() -> new AgentNotFoundException());

        agent.setName(dto.name());
        agent.setAgentStatus(dto.agentStatus());
        agent.setAgentType(dto.agentType());
        agent.setAuthKey(dto.authKey());
        agent.setCollectionInterval(dto.collectionInterval());
        agent.setPlatform(dto.platform());
        agent.setLastUpdatedBy(authenticatedUser);
        agent.setUpdatedAt(OffsetDateTime.now());

        AgentEntity agentSaved = repository.save(agent);

        return new AgentResponseDTO(
            agentSaved.getId(),
            agentSaved.getName(),
            agentSaved.getUserEntity().getId(),
            agentSaved.getAgentType(),
            agentSaved.getAgentStatus(),
            agentSaved.getAuthKey(),
            agentSaved.getPlatform(),
            agentSaved.getTags(),
            agentSaved.getDataProcessed(),
            agentSaved.getLastActivityAt(),
            agentSaved.getCreatedAt(),
            agentSaved.getUpdatedAt(),
            agentSaved.getCollectionInterval()
        );
    }

    public List<AgentResponseDTO> getAll() {
        List<AgentEntity> agents = repository.findAll();

        return agents.stream()
                .map(agent -> new AgentResponseDTO(
                    agent.getId(),
                    agent.getName(),
                    agent.getUserEntity().getId(),
                    agent.getAgentType(),
                    agent.getAgentStatus(),
                    agent.getAuthKey(),
                    agent.getPlatform(),
                    agent.getTags(),
                    agent.getDataProcessed(),
                    agent.getLastActivityAt(),
                    agent.getCreatedAt(),
                    agent.getUpdatedAt(),
                    agent.getCollectionInterval()
                ))
                .collect(Collectors.toList());
    }

    public AgentResponseDTO getById(Long id) {
        AgentEntity agent = repository.findById(id).orElseThrow(() -> new AgentNotFoundException());

        return new AgentResponseDTO(
            id,
            agent.getName(),
            agent.getUserEntity().getId(),
            agent.getAgentType(),
            agent.getAgentStatus(),
            agent.getAuthKey(),
            agent.getPlatform(),
            agent.getTags(),
            agent.getDataProcessed(),
            agent.getLastActivityAt(),
            agent.getCreatedAt(),
            agent.getUpdatedAt(),
            agent.getCollectionInterval()
        );
    }

    @Transactional
    public void delete(Long id) {
        AgentEntity agent = repository.findById(id)
            .orElseThrow(() -> new AgentNotFoundException());

        repository.delete(agent);
    }
}
