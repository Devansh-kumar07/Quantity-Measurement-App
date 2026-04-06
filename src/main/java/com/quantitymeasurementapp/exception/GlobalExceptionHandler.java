package com.quantitymeasurementapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler - Ek jagah se sab exceptions handle karo.
 *
 * @RestControllerAdvice = @ControllerAdvice + @ResponseBody
 * Har controller mein try-catch likhne ki zarurat nahi.
 * Ye class sab @RestController se aane wali exceptions pakdta hai.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // =============================================
    // 1. VALIDATION ERRORS (400)
    //    @Valid annotation fail hone par - field-level errors
    // =============================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        // Collect all field-level errors into a map: {"fieldName": "error message"}
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorResponse response = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Failed",
            "One or more fields have invalid values",
            request.getRequestURI()
        );
        response.setFieldErrors(fieldErrors);

        log.warn("Validation error on {}: {}", request.getRequestURI(), fieldErrors);
        return ResponseEntity.badRequest().body(response);
    }

    // =============================================
    // 2. RESOURCE NOT FOUND (404)
    // =============================================
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {

        log.warn("Resource not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ErrorResponse(404, "Not Found", ex.getMessage(), request.getRequestURI())
        );
    }

    // =============================================
    // 3. INVALID UNIT (400)
    // =============================================
    @ExceptionHandler(InvalidUnitException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUnit(
            InvalidUnitException ex, HttpServletRequest request) {

        log.warn("Invalid unit: {}", ex.getUnit());
        return ResponseEntity.badRequest().body(
            new ErrorResponse(400, "Invalid Unit", ex.getMessage(), request.getRequestURI())
        );
    }

    // =============================================
    // 4. INCOMPATIBLE UNITS (422)
    // =============================================
    @ExceptionHandler(IncompatibleUnitException.class)
    public ResponseEntity<ErrorResponse> handleIncompatibleUnits(
            IncompatibleUnitException ex, HttpServletRequest request) {

        log.warn("Incompatible units: {} and {}", ex.getUnitA(), ex.getUnitB());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            new ErrorResponse(422, "Incompatible Units", ex.getMessage(), request.getRequestURI())
        );
    }

    // =============================================
    // 5. USER ALREADY EXISTS (409)
    // =============================================
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(
            UserAlreadyExistsException ex, HttpServletRequest request) {

        log.warn("Duplicate registration attempt: {}", ex.getUsername());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new ErrorResponse(409, "Conflict", ex.getMessage(), request.getRequestURI())
        );
    }

    // =============================================
    // 6. DATABASE ERRORS (500)
    // =============================================
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(
            DatabaseException ex, HttpServletRequest request) {

        log.error("Database error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorResponse(500, "Database Error", "A database error occurred. Please try again later.", request.getRequestURI())
        );
    }

    // =============================================
    // 7. BAD CREDENTIALS (401) - wrong username/password
    // =============================================
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(
            BadCredentialsException ex, HttpServletRequest request) {

        log.warn("Bad credentials attempt at {}", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new ErrorResponse(401, "Unauthorized", "Invalid username or password", request.getRequestURI())
        );
    }

    // =============================================
    // 8. AUTHENTICATION EXCEPTION (401) - missing/expired token
    // =============================================
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException ex, HttpServletRequest request) {

        log.warn("Authentication failed at {}: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new ErrorResponse(401, "Unauthorized", "Authentication is required to access this resource", request.getRequestURI())
        );
    }

    // =============================================
    // 9. ACCESS DENIED (403) - authenticated but no permission
    // =============================================
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex, HttpServletRequest request) {

        log.warn("Access denied at {}", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ErrorResponse(403, "Forbidden", "You do not have permission to access this resource", request.getRequestURI())
        );
    }

    // =============================================
    // 10. MISSING REQUEST PARAMETER (400)
    //     e.g., /convert called without ?targetUnit=
    // =============================================
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(
            MissingServletRequestParameterException ex, HttpServletRequest request) {

        log.warn("Missing parameter '{}' at {}", ex.getParameterName(), request.getRequestURI());
        return ResponseEntity.badRequest().body(
            new ErrorResponse(400, "Bad Request",
                "Required parameter '" + ex.getParameterName() + "' is missing",
                request.getRequestURI())
        );
    }

    // =============================================
    // 11. TYPE MISMATCH (400)
    //     e.g., passing a string where a number is expected
    // =============================================
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String message = String.format(
            "Parameter '%s' should be of type '%s'",
            ex.getName(),
            ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown"
        );
        log.warn("Type mismatch at {}: {}", request.getRequestURI(), message);
        return ResponseEntity.badRequest().body(
            new ErrorResponse(400, "Bad Request", message, request.getRequestURI())
        );
    }

    // =============================================
    // 12. ARITHMETIC EXCEPTION (400)
    //     e.g., division by zero
    // =============================================
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponse> handleArithmetic(
            ArithmeticException ex, HttpServletRequest request) {

        log.warn("Arithmetic error at {}: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity.badRequest().body(
            new ErrorResponse(400, "Bad Request", "Arithmetic error: " + ex.getMessage(), request.getRequestURI())
        );
    }

    // =============================================
    // 13. ILLEGAL ARGUMENT (400)
    //     e.g., enum not found, null quantity value
    // =============================================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, HttpServletRequest request) {

        log.warn("Illegal argument at {}: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity.badRequest().body(
            new ErrorResponse(400, "Bad Request", ex.getMessage(), request.getRequestURI())
        );
    }

    // =============================================
    // 14. CATCH-ALL (500) - unexpected errors
    //     Always the last handler - sab bacha hua pakdo
    // =============================================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, HttpServletRequest request) {

        // Full stack trace log karo (production mein investigate karne ke liye)
        log.error("Unexpected error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            new ErrorResponse(500, "Internal Server Error", "An unexpected error occurred. Please try again later.", request.getRequestURI())
        );
    }
}
