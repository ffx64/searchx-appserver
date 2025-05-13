package com.ffx64.searchx_api.service.combolist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.combolist.FileResponseDTO;
import com.ffx64.searchx_api.entity.combolist.FileEntity;
import com.ffx64.searchx_api.repository.combolist.FileRepository;

@Service("FileServiceCombolist")
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public List<FileResponseDTO> getAllFiles() {
        return fileRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FileResponseDTO getFileById(Long id) {
        return fileRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public List<FileResponseDTO> getFilesByAgentKey(String agentKey) {
        return fileRepository.findByAgentKey(agentKey).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<FileResponseDTO> getFilesByStatus(Integer status) {
        return fileRepository.findByStatus(status).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private FileResponseDTO toDTO(FileEntity entity) {
        return new FileResponseDTO(
            entity.getId(),
            entity.getAgentKey(),
            entity.getName(),
            entity.getSize(),
            entity.getHash(),
            entity.getCreatedAt(),
            entity.getProcessedAt(),
            entity.getStatus(),
            entity.getSource(),
            entity.getType(),
            entity.getDescription(),
            entity.getProcessedEntriesCount()
        );
    }
}