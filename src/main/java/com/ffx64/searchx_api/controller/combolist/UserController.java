package com.ffx64.searchx_api.controller.combolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.combolist.UserResponseDTO;
import com.ffx64.searchx_api.service.combolist.UserService;

@RestController
@RequestMapping("/v1/api/combolist/users")
@Controller("UserControllerCombolist")
public class UserController {

    @Autowired
    @Qualifier("UserServiceCombolist")
    private UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/by-username/{username}")
    public List<UserResponseDTO> getUsersByUsername(@PathVariable String username) {
        return userService.getUsersByUsername(username);
    }

    @GetMapping("/by-password/{password}")
    public List<UserResponseDTO> getUsersByPassword(@PathVariable String password) {
        return userService.getUsersByPassword(password);
    }

    @GetMapping("/by-file/{fileId}")
    public List<UserResponseDTO> getUsersByFileId(@PathVariable Long fileId) {
        return userService.getUsersByFileId(fileId);
    }
}
