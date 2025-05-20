package com.ffx64.searchx_api.infra.security;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ffx64.searchx_api.dto.auth.TokenResponseDTO;
import com.ffx64.searchx_api.entity.main.UserEntity;
import com.ffx64.searchx_api.exception.ExpiredTokenException;
import com.ffx64.searchx_api.exception.GenericException;
import com.ffx64.searchx_api.exception.UserNotFoundException;
import com.ffx64.searchx_api.repository.main.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    private static final String BLACKLIST_PREFIX = "blacklist:";
    private static final String REFRESH_PREFIX = "refresh:";
    private static final String ISSUER = "searchx-api";

    private static final long ACCESS_TOKEN_EXPIRATION_MINUTES = 15;
    private static final long REFRESH_TOKEN_EXPIRATION_DAYS = 7;

    public String generateAccessToken(UserDetails user) {
        return generateToken(user.getUsername(), Duration.ofMinutes(ACCESS_TOKEN_EXPIRATION_MINUTES).getSeconds());
    }

    public String generateRefreshToken(UserDetails user) {
        String token = generateToken(user.getUsername(), Duration.ofDays(REFRESH_TOKEN_EXPIRATION_DAYS).getSeconds());
        String redisKey = REFRESH_PREFIX + token;
        redisTemplate.opsForValue().set(redisKey, user.getUsername(), Duration.ofDays(REFRESH_TOKEN_EXPIRATION_DAYS));
        return token;
    }

    private String generateToken(String username, long expirationInSeconds){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(username)
                    .withExpiresAt(Instant.now().plusSeconds(expirationInSeconds))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new GenericException();
        }
    }

    public Optional<String> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            String username = verifier.verify(token).getSubject();
            if (isTokenRevoked(token)) {
                return Optional.empty();
            }
            return Optional.of(username);
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    public Optional<String> validateRefreshToken(String token) {
        String redisKey = REFRESH_PREFIX + token;

        if (!redisTemplate.hasKey(redisKey)) {
            return Optional.empty();
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            return Optional.of(verifier.verify(token).getSubject());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    public boolean isValid(String token) {
        if (isTokenRevoked(token)) return false;
        return validateToken(token).isPresent();
    }

    public void revokeToken(String token, long expirationSeconds) {
        redisTemplate.opsForValue().set(BLACKLIST_PREFIX + token, "revoked", Duration.ofSeconds(expirationSeconds));
        redisTemplate.delete(REFRESH_PREFIX + token); // remove da lista de refresh
    }

    public boolean isTokenRevoked(String token) {
        return redisTemplate.hasKey(BLACKLIST_PREFIX + token);
    }

    public Optional<TokenResponseDTO> refreshAccessToken(String refreshToken) {
        var maybeUsername = validateRefreshToken(refreshToken);
        if (maybeUsername.isEmpty()) {
            return Optional.empty();
        }

        String username = maybeUsername.get();
        revokeToken(refreshToken, Duration.ofDays(REFRESH_TOKEN_EXPIRATION_DAYS).getSeconds());

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        String newAccessToken = generateAccessToken(user);
        String newRefreshToken = generateRefreshToken(user);

        return Optional.of(new TokenResponseDTO(newAccessToken, newRefreshToken));
    }

    public long getExpirationSeconds(String token) {
        DecodedJWT decoded = getTokenClaims(token);
        Instant expiresAt = decoded.getExpiresAt().toInstant();
        Instant now = Instant.now();
        long secondsLeft = Duration.between(now, expiresAt).getSeconds();
        return secondsLeft > 0 ? secondsLeft : 0;
    }

    public UserEntity getAuthenticatedUser() {
        String token = extractTokenFromHeader();
        DecodedJWT decodedJWT = getTokenClaims(token);
        return userRepository.findByUsername(decodedJWT.getSubject())
                .orElseThrow(UserNotFoundException::new);
    }

    public DecodedJWT getTokenClaims(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ExpiredTokenException();
        }
    }

    public String extractTokenFromHeader() {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        
        throw new GenericException("jwt token not found in the authorization header.");
    }
}
