package com.ffx64.searchx_api.service.searchx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.entity.searchx.AgentEntity;
import com.ffx64.searchx_api.entity.searchx.UserEntity;
import com.ffx64.searchx_api.exception.AgentNotFoundExecption;
import com.ffx64.searchx_api.exception.UserNotFoundException;
import com.ffx64.searchx_api.repository.searchx.AgentRepository;
import com.ffx64.searchx_api.repository.searchx.UserRepository;

@Service("AgentServiceSearchx")
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    public AgentEntity createAgent(AgentEntity agent) {
        UserEntity user = userRepository.findById(agent.getUser().getId())
                .orElseThrow(UserNotFoundException::new);

        agent.setUser(user);
        AgentEntity saved = agentRepository.save(agent);

        user.setAgentsCount(user.getAgentsCount() + 1);
        userRepository.save(user);

        return saved;
    }

    public List<AgentEntity> getAgentsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        return agentRepository.findByUserId(userId);
    }

    public AgentEntity getAgentById(Long id) {
        return agentRepository.findById(id)
                .orElseThrow(AgentNotFoundExecption::new);
    }
}
