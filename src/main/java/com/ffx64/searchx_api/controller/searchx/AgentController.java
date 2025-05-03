package com.ffx64.searchx_api.controller.searchx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.entity.searchx.AgentEntity;
import com.ffx64.searchx_api.service.searchx.AgentService;

@RestController
@RequestMapping("/v1/api/agents")
@Controller("agentControllerSearchx")
public class AgentController {

    @Autowired
    @Qualifier("AgentServiceSearchx")
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<?> createAgent(@RequestBody AgentEntity agent) {
        AgentEntity saved = agentService.createAgent(agent);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<AgentEntity>> getAgentsByUser(@PathVariable Long userId) {
        List<AgentEntity> agents = agentService.getAgentsByUser(userId);
        if (agents.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAgentById(@PathVariable Long id) {
        AgentEntity agent = agentService.getAgentById(id);
        return ResponseEntity.ok(agent);
    }
}
