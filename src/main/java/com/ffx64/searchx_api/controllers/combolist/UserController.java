package com.ffx64.searchx_api.controllers.combolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.entities.combolist.UserEntity;
import com.ffx64.searchx_api.repositories.combolist.UserRepository;

@RestController
@RequestMapping("/v1/api/combolist/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/by-username/{username}")
    public List<UserEntity> getUsersByUsername(@PathVariable String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @GetMapping("/by-password/{password}")
    public List<UserEntity> getUsersByPassword(@PathVariable String password) {
        return userRepository.findByPasswordIgnoreCase(password);
    }

    @GetMapping("/by-file/{fileId}")
    public List<UserEntity> getUsersByFileId(@PathVariable Long fileId) {
        return userRepository.findByFileId(fileId);
    }
}
