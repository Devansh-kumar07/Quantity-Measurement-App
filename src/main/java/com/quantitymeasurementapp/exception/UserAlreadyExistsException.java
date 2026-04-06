package com.quantitymeasurementapp.exception;

/**
 * Thrown when attempting to register a username that already exists.
 * Maps to HTTP 409 Conflict.
 */
public class UserAlreadyExistsException extends RuntimeException {

    private final String username;

    public UserAlreadyExistsException(String username) {
        super("User already exists with username: '" + username + "'");
        this.username = username;
    }

    public String getUsername() { return username; }
}
