package com.ffx64.searchx_api.service.searchx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.searchx.UserRequestDTO;
import com.ffx64.searchx_api.dto.searchx.UserResponseDTO;
import com.ffx64.searchx_api.entity.searchx.UserEntity;
import com.ffx64.searchx_api.exception.UserNotFoundException;
import com.ffx64.searchx_api.repository.searchx.UserRepository;
import com.ffx64.searchx_api.utils.Hash;

@Service("UserServiceSearchx")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(Hash.GenerateSha256(dto.getPassword()));

        UserEntity saved = userRepository.save(entity);
        return toDTO(saved);
    }

    public UserResponseDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());
        return toDTO(user);
    }

    private UserResponseDTO toDTO(UserEntity entity) {
        return UserResponseDTO.builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .agentsCount(entity.getAgentsCount())
            .createdAt(entity.getCreatedAt())
            .build();
    }
}
