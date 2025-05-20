package com.ffx64.searchx_api.controller.combolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.combolist.MetadataResponseDTO;
import com.ffx64.searchx_api.service.combolist.MetadataService;

@RestController
@RequestMapping("/v1/api/combolist/metadata")
public class MetadataController {

    @Autowired
    MetadataService service;

    @GetMapping("/{id}")
    public ResponseEntity<MetadataResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<Page<MetadataResponseDTO>> getAllPaginated(@PathVariable int page) {
        return ResponseEntity.ok(service.getAllPaginated(page));
    }
}
