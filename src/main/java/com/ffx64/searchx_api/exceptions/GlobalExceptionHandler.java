package com.ffx64.searchx_api.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, String> body = new HashMap<>();
        body.put("error", error);
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return buildErrorResponse(
            HttpStatus.NOT_FOUND,
            "USER_NOT_FOUND",
            "The specified user could not be located. Please verify the user ID and try again."
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex) {
        return buildErrorResponse(
            HttpStatus.NOT_FOUND,
            "ENTITY_NOT_FOUND",
            "The requested entity could not be found. Please ensure the provided information is accurate."
        );
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<?> handleUserExists(UserExistsException ex) {
        return buildErrorResponse(
            HttpStatus.CONFLICT,
            "USERNAME_ALREADY_EXISTS",
            "The chosen username is already in use. Please select a different one."
        );
    }

    @ExceptionHandler(AgentNotFoundExecption.class)
    public ResponseEntity<?> handleAgentNotFound(AgentNotFoundExecption ex) {
        return buildErrorResponse(
            HttpStatus.NOT_FOUND,
            "AGENT_NOT_FOUND",
            "The specified agent could not be located. Please verify the user ID and try again."
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return buildErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "INTERNAL_ERROR",
            "An unexpected server error occurred. Please try again later."
        );
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationException(javax.validation.ConstraintViolationException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, 
        "BAD_REQUEST",
         "One or more fields failed validation. Please review your input and try again."
         );
    }

}
