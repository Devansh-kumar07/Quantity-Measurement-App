package com.quantitymeasurementapp.auth;

// Login successful hone ke baad ye response jaata hai client ko:
// {
//   "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWh1bCJ9...",
//   "message": "Login successful"
// }
public class AuthResponse {

    private String token;
    private String message;

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() { return token; }
    public String getMessage() { return message; }
}
