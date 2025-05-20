package com.ffx64.searchx_api.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffx64.searchx_api.dto.auth.TokenResponseDTO;
import com.ffx64.searchx_api.dto.main.UserRequestDTO;
import com.ffx64.searchx_api.entity.main.UserEntity;
import com.ffx64.searchx_api.exception.ExpiredTokenException;
import com.ffx64.searchx_api.exception.InvalidCredentialsException;
import com.ffx64.searchx_api.infra.security.TokenService;
import com.ffx64.searchx_api.repository.main.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsernameDetails(username);
    }

    @Transactional
    public TokenResponseDTO login(UserRequestDTO dto) {
        UserEntity user = userRepository.findByUsername(dto.username())
            .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        var authToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = authenticationManager.authenticate(authToken);

        var userDetails = (UserDetails) auth.getPrincipal();
        var accessToken = tokenService.generateAccessToken(userDetails);
        var refreshToken = tokenService.generateRefreshToken(userDetails);

        userRepository.updateLastLogin(user.getId());

        return new TokenResponseDTO(accessToken, refreshToken);
    }

    public void logout(String accessToken, String refreshToken) {
        if (accessToken != null && tokenService.isValid(accessToken)) {
            tokenService.revokeToken(accessToken, tokenService.getExpirationSeconds(accessToken));
        }
        if (refreshToken != null && tokenService.isValid(refreshToken)) {
            tokenService.revokeToken(refreshToken, tokenService.getExpirationSeconds(refreshToken));
        }
    }

    public TokenResponseDTO refreshToken(String refreshToken) {
        if (refreshToken == null || !tokenService.isValid(refreshToken) || tokenService.isTokenRevoked(refreshToken)) {
            throw new ExpiredTokenException();
        }

        String username = tokenService.validateToken(refreshToken).get();
        UserDetails userDetails = loadUserByUsername(username);

        String newAccessToken = tokenService.generateAccessToken(userDetails);

        return new TokenResponseDTO(newAccessToken, refreshToken);
    }
}
