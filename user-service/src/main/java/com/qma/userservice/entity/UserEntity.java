package com.qma.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min=3,max=50) @Pattern(regexp="^[a-zA-Z0-9_]+$")
    @Column(unique=true, nullable=false)
    private String username;

    @NotBlank @Column(nullable=false)
    private String password;

    @NotBlank @Column(nullable=false)
    private String role;

    @Column(name="google_id", unique=true)
    private String googleId;

    @Email @Column(unique=true)
    private String email;

    @Column(name="display_name")
    private String displayName;

    public UserEntity() {}

    public UserEntity(String username, String password, String role) {
        this.username=username; this.password=password; this.role=role;
    }

    public UserEntity(String username, String email, String displayName, String googleId, String role) {
        this.username=username; this.email=email; this.displayName=displayName;
        this.googleId=googleId; this.password="GOOGLE_AUTH_NO_PASSWORD"; this.role=role;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; } public void setUsername(String u) { username=u; }
    public String getPassword() { return password; } public void setPassword(String p) { password=p; }
    public String getRole() { return role; } public void setRole(String r) { role=r; }
    public String getGoogleId() { return googleId; } public void setGoogleId(String g) { googleId=g; }
    public String getEmail() { return email; } public void setEmail(String e) { email=e; }
    public String getDisplayName() { return displayName; } public void setDisplayName(String d) { displayName=d; }
}
