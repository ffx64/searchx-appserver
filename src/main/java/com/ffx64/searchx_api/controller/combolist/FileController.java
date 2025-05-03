package com.ffx64.searchx_api.controller.combolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.combolist.FileResponseDTO;
import com.ffx64.searchx_api.service.combolist.FileService;

@RestController
@RequestMapping("/v1/api/combolist/files")
@Controller("FileControllerCombolist")
public class FileController {

    @Autowired
    @Qualifier("FileServiceCombolist")
    private FileService fileService;

    @GetMapping
    public List<FileResponseDTO> getAllFiles() {
        return fileService.getAllFiles();
    }

    @GetMapping("/{id}")
    public FileResponseDTO getFileById(@PathVariable Long id) {
        return fileService.getFileById(id);
    }

    @GetMapping("/by-agent/{agentKey}")
    public List<FileResponseDTO> getFilesByAgentKey(@PathVariable String agentKey) {
        return fileService.getFilesByAgentKey(agentKey);
    }

    @GetMapping("/by-status/{status}")
    public List<FileResponseDTO> getFilesByStatus(@PathVariable Integer status) {
        return fileService.getFilesByStatus(status);
    }
}
