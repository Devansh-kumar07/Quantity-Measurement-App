package com.qma.userservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    record ErrorResponse(int status, String error, String message, String path, LocalDateTime timestamp, Map<String,String> fieldErrors) {}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String,String> fe = new HashMap<>();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) fe.put(e.getField(), e.getDefaultMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(400,"Validation Failed","Invalid fields",req.getRequestURI(),LocalDateTime.now(),fe));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserExists(UserAlreadyExistsException ex, HttpServletRequest req) {
        return ResponseEntity.status(409).body(new ErrorResponse(409,"Conflict",ex.getMessage(),req.getRequestURI(),LocalDateTime.now(),null));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCreds(BadCredentialsException ex, HttpServletRequest req) {
        return ResponseEntity.status(401).body(new ErrorResponse(401,"Unauthorized","Invalid username or password",req.getRequestURI(),LocalDateTime.now(),null));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuth(AuthenticationException ex, HttpServletRequest req) {
        return ResponseEntity.status(401).body(new ErrorResponse(401,"Unauthorized","Authentication required",req.getRequestURI(),LocalDateTime.now(),null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest req) {
        log.error("Error at {}: {}", req.getRequestURI(), ex.getMessage(), ex);
        return ResponseEntity.status(500).body(new ErrorResponse(500,"Internal Server Error","An unexpected error occurred.",req.getRequestURI(),LocalDateTime.now(),null));
    }
}
