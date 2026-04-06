package com.quantitymeasurementapp.auth;

import com.quantitymeasurementapp.entity.UserEntity;
import com.quantitymeasurementapp.exception.UserAlreadyExistsException;
import com.quantitymeasurementapp.repository.UserRepository;
import com.quantitymeasurementapp.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    // =============================================
    // REGISTER - POST /auth/register
    // Body: {"username":"rahul","password":"secret123"}
    // @Valid - AuthRequest ke @NotBlank, @Size etc. validate karega
    //          Agar fail ho -> GlobalExceptionHandler -> 400 with field errors
    // =============================================
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthRequest request) {

        // UserAlreadyExistsException -> GlobalExceptionHandler -> 409 Conflict
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
        return ResponseEntity.ok("Registered successfully: " + request.getUsername());
    }

    // =============================================
    // LOGIN - POST /auth/login
    // Body: {"username":"rahul","password":"secret123"}
    // BadCredentialsException -> GlobalExceptionHandler -> 401 Unauthorized
    // =============================================
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {

        // authenticate() throws BadCredentialsException on failure
        // GlobalExceptionHandler pakdta hai ise - try-catch ki zarurat nahi
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
    }

    // =============================================
    // GOOGLE LOGIN INFO - GET /auth/google
    // Sirf info endpoint - actual login /oauth2/authorize/google pe hota hai
    // =============================================
    @GetMapping("/google")
    public ResponseEntity<String> googleLoginInfo() {
        return ResponseEntity.ok(
            "To login with Google, redirect your browser to: /oauth2/authorize/google"
        );
    }
}
