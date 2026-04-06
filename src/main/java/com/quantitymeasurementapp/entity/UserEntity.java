package com.quantitymeasurementapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank  - null, empty string, whitespace-only - sab reject
    // @Size      - username 3 to 50 characters ke beech hona chahiye
    // @Pattern   - only alphanumeric + underscore allowed (no spaces, no special chars)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, digits, and underscores")
    @Column(unique = true, nullable = false)
    private String username;

    // @NotBlank  - password empty nahi honi chahiye
    // @Size      - minimum 6 characters enforce karo
    // Note: validation raw password par hoga (before encoding)
    //       DB mein encoded password store hoga, isliye @Size DB constraint nahi hai
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(nullable = false)
    private String password;

    // Role - allowed values sirf "ROLE_USER" ya "ROLE_ADMIN"
    @NotBlank(message = "Role is required")
    @Pattern(regexp = "^ROLE_(USER|ADMIN)$", message = "Role must be ROLE_USER or ROLE_ADMIN")
    @Column(nullable = false)
    private String role;

    // Google OAuth2 fields - null allowed (regular users ke liye)
    // googleId - Google ka unique user identifier (sub claim)
    @Column(name = "google_id", unique = true)
    private String googleId;

    // email - Google se milta hai; regular users ke liye optional
    @Email(message = "Email must be a valid email address")
    @Column(unique = true)
    private String email;

    // name - Google se milta hai (display name)
    @Column(name = "display_name")
    private String displayName;

    // No-arg constructor - JPA ke liye zaruri
    public UserEntity() {}

    // Constructor for regular registration
    public UserEntity(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Constructor for Google OAuth2 login
    public UserEntity(String username, String email, String displayName, String googleId, String role) {
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.googleId = googleId;
        this.password = "GOOGLE_AUTH_NO_PASSWORD"; // placeholder - Google users ko password nahi chahiye
        this.role = role;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getGoogleId() { return googleId; }
    public void setGoogleId(String googleId) { this.googleId = googleId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
}
