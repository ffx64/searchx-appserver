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
                .map(this::toDTO)
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

    private FileResponseDTO toFileDTO(FileEntity entity) {
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
    
    private UserResponseDTO toDTO(UserEntity entity) {
        return new UserResponseDTO(
            entity.getId(),
            this.toFileDTO(entity.getFile()),
            entity.getUsername(),
            entity.getPassword(),
            entity.getCreatedAt()
        );
    }
}
