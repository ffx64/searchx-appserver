package com.ffx64.searchx_api.service.searchx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx64.searchx_api.dto.searchx.UserResponseDTO;
import com.ffx64.searchx_api.entity.searchx.UserEntity;
import com.ffx64.searchx_api.exception.UserNotFoundException;
import com.ffx64.searchx_api.repository.searchx.UserRepository;

@Service("UserServiceSearchx")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());
        return toDTO(user);
    }

    private UserResponseDTO toDTO(UserEntity entity) {
        return new UserResponseDTO(
            entity.getId(),
            entity.getUsername(),
            entity.getAgentsCount(),
            entity.getCreatedAt()
            );
    }
}
