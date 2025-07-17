package com.ffx64.searchx_api.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.auth.TokenResponseDTO;
import com.ffx64.searchx_api.dto.main.UserRequestDTO;
import com.ffx64.searchx_api.dto.main.UserResponseDTO;
import com.ffx64.searchx_api.infra.security.TokenService;
import com.ffx64.searchx_api.service.auth.AuthenticationService;
import com.ffx64.searchx_api.service.main.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    UserService userService;

    @GetMapping("/default")
    public ResponseEntity<String> defaultx() {

        userService.create(new UserRequestDTO("searchx", "SEARCHX DEFAULT USER", null, null, "secret"));

        return ResponseEntity.ok("searchx:secret");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UserRequestDTO dto) {
        TokenResponseDTO tokens = authenticationService.login(dto);

        return ResponseEntity.ok(tokens);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refresh(HttpServletRequest request) {
        String refreshToken = request.getHeader("Refresh-Token");
        var tokens = authenticationService.refreshToken(refreshToken);

        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String accessToken = tokenService.extractTokenFromHeader();
        String refreshToken = request.getHeader("Refresh-Token");

        authenticationService.logout(accessToken, refreshToken);

        return ResponseEntity.ok("token cancelado.");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getInformationsAccount() {
        UserResponseDTO user = userService.getAuthenticated();

        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateInformationsAccount(@RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO updated = userService.update(dto);

        return ResponseEntity.ok(updated);
    }
}
