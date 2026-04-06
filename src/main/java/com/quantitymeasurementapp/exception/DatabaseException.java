package com.quantitymeasurementapp.exception;

/**
 * Thrown when a database operation fails unexpectedly (e.g., connection issues, constraint violations).
 * Maps to HTTP 500 Internal Server Error.
 */
public class DatabaseException extends RuntimeException {

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
