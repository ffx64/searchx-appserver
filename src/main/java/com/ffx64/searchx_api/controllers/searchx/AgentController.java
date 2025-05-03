package com.ffx64.searchx_api.controllers.searchx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.entities.searchx.AgentEntity;
import com.ffx64.searchx_api.entities.searchx.UserEntity;
import com.ffx64.searchx_api.exceptions.AgentNotFoundExecption;
import com.ffx64.searchx_api.exceptions.UserNotFoundException;
import com.ffx64.searchx_api.repositories.searchx.AgentRepository;
import com.ffx64.searchx_api.repositories.searchx.UserRepository;

@RestController
@RequestMapping("/v1/api/agents")
@Controller("agentControllerSearchx")
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createAgent(@RequestBody AgentEntity agent) {
        UserEntity user = userRepository.findById(agent.getUser().getId()).orElseThrow(() -> new UserNotFoundException());

        agent.setUser(user);
        AgentEntity saved = agentRepository.save(agent);

        user.setAgentsCount(user.getAgentsCount() + 1);
        userRepository.save(user);

        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<AgentEntity>> getAgentsByUser(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
    
        List<AgentEntity> agents = agentRepository.findByUserId(userId);
    
        if (agents.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
    
        return ResponseEntity.ok(agents);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable Long id) {
        return agentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new AgentNotFoundExecption());
    }
}
