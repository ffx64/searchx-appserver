package com.ffx64.searchx_api.service.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffx64.searchx_api.dto.main.UserRequestDTO;
import com.ffx64.searchx_api.dto.main.UserResponseDTO;
import com.ffx64.searchx_api.entity.main.UserEntity;
import com.ffx64.searchx_api.infra.security.TokenService;
import com.ffx64.searchx_api.repository.main.UserRepository;

@Service("MainUserService")
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    TokenService tokenService;

    @Transactional
    public UserResponseDTO create(UserRequestDTO dto) {
        UserEntity user = new UserEntity();

        user.setUsername(dto.username());
        user.setFullName(dto.fullName());
        user.setEmail(dto.email());
        user.setRole(dto.role());
        user.setPasswordHash(dto.password());
        user.setIsActive(true);

        user = repository.save(user);

        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getFullName(),
            user.getEmail(),
            user.getRole(),
            user.getIsActive(),
            null,
            null
        );
    }

    @Transactional
    public UserResponseDTO update(UserRequestDTO dto) {
        UserEntity user = tokenService.getAuthenticatedUser();

        user.setUsername(dto.username());
        user.setFullName(dto.fullName());
        user.setEmail(dto.email());
        user.setRole(dto.role());

        user = repository.save(user);

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getLastLogin()
        );
    }

    public UserResponseDTO getAuthenticated() {
        UserEntity user = tokenService.getAuthenticatedUser();

        return new UserResponseDTO(user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getLastLogin()
        );
    }

    public List<UserResponseDTO> getAll() {
        List<UserEntity> users = repository.findAll();

        return users.stream()
            .map(user -> new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                null,
                user.getRole(),
                null,
                user.getCreatedAt(),
                user.getLastLogin()
            ))
            .toList();
    }
}
