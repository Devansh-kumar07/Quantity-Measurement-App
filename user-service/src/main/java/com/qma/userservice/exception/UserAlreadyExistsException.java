package com.qma.userservice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private final String username;
    public UserAlreadyExistsException(String username) {
        super("User already exists: '" + username + "'");
        this.username = username;
    }
    public String getUsername() { return username; }
}
