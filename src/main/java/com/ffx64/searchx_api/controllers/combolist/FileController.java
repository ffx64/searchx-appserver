package com.ffx64.searchx_api.controllers.combolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.entities.combolist.FileEntity;
import com.ffx64.searchx_api.repositories.combolist.FileRepository;

@RestController
@RequestMapping("/v1/api/combolist/files")
@Controller("fileControllerCombolist")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @GetMapping
    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    @GetMapping("/{id}")
    public FileEntity getFileById(@PathVariable Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    @GetMapping("/by-agent/{agentKey}")
    public List<FileEntity> getFilesByAgentKey(@PathVariable String agentKey) {
        return fileRepository.findByAgentKey(agentKey);
    }
    
    @GetMapping("/by-status/{status}")
    public List<FileEntity> getFilesByStatus(@PathVariable Integer status) {
        return fileRepository.findByStatus(status);
    }

}
