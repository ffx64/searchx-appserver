package com.ffx64.searchx_api.controller.combolist;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.combolist.DataMResponseDTO;
import com.ffx64.searchx_api.dto.combolist.DataResponseDTO;
import com.ffx64.searchx_api.service.combolist.DataService;

@RestController
@RequestMapping("/api/v1/combolist/data")
public class DataController {

    @Autowired
    private DataService service;

    @GetMapping("/{id}")
    public ResponseEntity<DataMResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<DataResponseDTO>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.getByEmail(email));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<DataResponseDTO>> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.getByUsername(username));
    }

    @GetMapping("/password/{password}")
    public ResponseEntity<List<DataResponseDTO>> getByPassword(@PathVariable String password) {
        return ResponseEntity.ok(service.getByPassword(password));
    }

    @GetMapping("/domain/{domain}")
    public ResponseEntity<List<DataResponseDTO>> getByDomain(@PathVariable String domain) {
        return ResponseEntity.ok(service.getByDomain(domain));
    }

    @GetMapping("/search/{input}")
    public ResponseEntity<List<DataResponseDTO>> getByMetadataAndSimilar(@PathVariable String input, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        List<DataResponseDTO> resultPage = service.getBySimilar(input.toLowerCase(), page, size);

        return ResponseEntity.ok(resultPage);
    }

    @GetMapping("/search/{metadataId}/{input}")
    public ResponseEntity<List<DataResponseDTO>> getByMetadataAndSimilar(@PathVariable UUID metadataId, @PathVariable String input, @RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="20") int size) {
        List<DataResponseDTO> resultPage = service.getByMetadataSimilar(metadataId, input.toLowerCase(), page, size);

        return ResponseEntity.ok(resultPage);
    }
}