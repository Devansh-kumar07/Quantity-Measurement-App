package com.qma.historyservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    record ErrorResponse(int status, String error, String message, String path, LocalDateTime timestamp) {}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest req) {
        log.error("Error at {}: {}", req.getRequestURI(), ex.getMessage(), ex);
        return ResponseEntity.status(500)
                .body(new ErrorResponse(500, "Internal Server Error",
                        "An unexpected error occurred.", req.getRequestURI(), LocalDateTime.now()));
    }
}
