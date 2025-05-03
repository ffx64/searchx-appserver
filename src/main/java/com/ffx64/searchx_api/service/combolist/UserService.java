package com.ffx64.searchx_api.service.combolist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.combolist.FileResponseDTO;
import com.ffx64.searchx_api.dto.combolist.UserResponseDTO;
import com.ffx64.searchx_api.entity.combolist.FileEntity;
import com.ffx64.searchx_api.entity.combolist.UserEntity;
import com.ffx64.searchx_api.repository.combolist.UserRepository;

@Service("UserServiceCombolist")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)  // Aqui usamos o método toDTO
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public List<UserResponseDTO> getUsersByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getUsersByPassword(String password) {
        return userRepository.findByPasswordIgnoreCase(password).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getUsersByFileId(Long fileId) {
        return userRepository.findByFileId(fileId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

        // Método para converter FileEntity para FileResponseDTO
    private FileResponseDTO toFileDTO(FileEntity fileEntity) {
        return FileResponseDTO.builder()
                .id(fileEntity.getId())
                .agentKey(fileEntity.getAgentKey())
                .name(fileEntity.getName())
                .size(fileEntity.getSize())
                .hash(fileEntity.getHash())
                .createdAt(fileEntity.getCreatedAt())
                .processedAt(fileEntity.getProcessedAt())
                .status(fileEntity.getStatus())
                .source(fileEntity.getSource())
                .type(fileEntity.getType())
                .description(fileEntity.getDescription())
                .processedEntriesCount(fileEntity.getProcessedEntriesCount())
                .build();
    }
    
    // Método toDTO para converter UserEntity para UserResponseDTO
    private UserResponseDTO toDTO(UserEntity userEntity) {
        return UserResponseDTO.builder()
                .id(userEntity.getId())
                .file(toFileDTO(userEntity.getFile()))  // Agora pegamos as informações do arquivo
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .build();
    }
}
