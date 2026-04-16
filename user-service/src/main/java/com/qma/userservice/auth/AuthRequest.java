package com.qma.userservice.auth;

import jakarta.validation.constraints.*;

public class AuthRequest {
    @NotBlank @Size(min=3,max=50) @Pattern(regexp="^[a-zA-Z0-9_]+$")
    private String username;
    @NotBlank @Size(min=6)
    private String password;
    public AuthRequest() {}
    public String getUsername() { return username; } public void setUsername(String u) { username=u; }
    public String getPassword() { return password; } public void setPassword(String p) { password=p; }
}
