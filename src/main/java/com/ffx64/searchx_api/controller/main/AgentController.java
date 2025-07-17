package com.ffx64.searchx_api.controller.main;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.main.AgentRequestDTO;
import com.ffx64.searchx_api.dto.main.AgentResponseDTO;
import com.ffx64.searchx_api.service.main.AgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    @Autowired
    private AgentService service;

    @PostMapping
    public ResponseEntity<AgentResponseDTO> create(@RequestBody @Valid AgentRequestDTO dto) {
        AgentResponseDTO createdAgent = service.create(dto);

        return ResponseEntity.ok(createdAgent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid AgentRequestDTO dto) {
        AgentResponseDTO updatedAgent = service.update(id, dto);

        return ResponseEntity.ok(updatedAgent);
    }

    @GetMapping
    public ResponseEntity<List<AgentResponseDTO>> getAll() {
        List<AgentResponseDTO> agents = service.getAll();

        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentResponseDTO> getById(@PathVariable("id") UUID id) {
        AgentResponseDTO agent = service.getById(id);
        
        return ResponseEntity.ok(agent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}