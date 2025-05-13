package com.ffx64.searchx_api.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.auth.TokenResponseDTO;
import com.ffx64.searchx_api.dto.searchx.UserRequestDTO;
import com.ffx64.searchx_api.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid UserRequestDTO user) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserDetails) auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
