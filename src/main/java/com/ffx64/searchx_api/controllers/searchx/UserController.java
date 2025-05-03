package com.ffx64.searchx_api.controllers.searchx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.entities.searchx.UserEntity;
import com.ffx64.searchx_api.exceptions.UserExistsException;
import com.ffx64.searchx_api.exceptions.UserNotFoundException;
import com.ffx64.searchx_api.repositories.searchx.UserRepository;
import com.ffx64.searchx_api.utils.Hash;

@RestController
@RequestMapping("/v1/api/users")
@Controller("userControllerSearchx")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserExistsException();
        }

        user.setPassword(Hash.GenerateSha256(user.getPassword()));
 
        UserEntity saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException());
    }
}
