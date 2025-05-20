package com.ffx64.searchx_api.infra.handler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ffx64.searchx_api.dto.exception.ExceptionResponseDTO;
import com.ffx64.searchx_api.exception.AccessDeniedException;
import com.ffx64.searchx_api.exception.AgentAndPlatformExistsException;
import com.ffx64.searchx_api.exception.ExpiredTokenException;
import com.ffx64.searchx_api.exception.GenericException;
import com.ffx64.searchx_api.exception.InvalidCredentialsException;
import com.ffx64.searchx_api.exception.UserExistsException;
import com.ffx64.searchx_api.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionResponseDTO> handleGeneric(GenericException ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserNotFound(UserNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserExists(UserExistsException ex) {
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ExceptionResponseDTO> handleExpiredToken(ExpiredTokenException ex) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
    
    @ExceptionHandler(AgentAndPlatformExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAgentAndPlatformExists(AgentAndPlatformExistsException ex) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAccessDenied(AccessDeniedException ex) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInvalidCredentials(InvalidCredentialsException ex) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    private ResponseEntity<ExceptionResponseDTO> build(HttpStatus status, String message) {
        var response = new ExceptionResponseDTO(
            status.getReasonPhrase(),
            message,
            status.value(),
            OffsetDateTime.now()
        );

        return new ResponseEntity<>(response, status);
    }
}
