package com.quantitymeasurementapp.security;

import com.quantitymeasurementapp.entity.UserEntity;
import com.quantitymeasurementapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Google OAuth2 Login Success Handler
 *
 * Ye tab execute hota hai jab Google successfully user ko authenticate kar deta hai.
 *
 * Flow:
 * 1. User /oauth2/authorize/google pe click karta hai
 * 2. Google login page dikhaata hai
 * 3. User login karta hai aur permission deta hai
 * 4. Google humari app ko redirect karta hai: /login/oauth2/code/google?code=...
 * 5. Spring OAuth2 client code exchange karta hai - access token milta hai
 * 6. Spring OAuth2 user info Google se fetch karta hai
 * 7. YE HANDLER chaleta hai - hum JWT banate hain aur frontend pe redirect karte hain
 */
@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(GoogleOAuth2SuccessHandler.class);

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Value("${app.oauth2.success-redirect-url:http://localhost:3000/oauth2/callback}")
    private String successRedirectUrl;

    public GoogleOAuth2SuccessHandler(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException {

        // Spring OAuth2 client - Google se milne wala user object
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Google se user info nikalten hain (Google ke standard attribute names)
        String googleId = oAuth2User.getAttribute("sub");       // Google ka unique user ID
        String email    = oAuth2User.getAttribute("email");     // user@gmail.com
        String name     = oAuth2User.getAttribute("name");      // "Rahul Sharma"

        if (email == null || googleId == null) {
            log.error("Google OAuth2: email or sub missing from token");
            response.sendRedirect(successRedirectUrl + "?error=google_auth_failed");
            return;
        }

        log.info("Google OAuth2 login: email={}, name={}", email, name);

        // DB mein check karo - pehle se registered hai ya nahi?
        Optional<UserEntity> existingUser = userRepository.findByGoogleId(googleId);

        UserEntity user;
        if (existingUser.isPresent()) {
            // Pehle se registered hai - bas existing user use karo
            user = existingUser.get();
            log.info("Existing Google user logged in: {}", email);
        } else {
            // Naaya user hai - auto-register karo
            // username = email ka local part (before @), agar conflict ho to googleId se banao
            String baseUsername = email.split("@")[0].replaceAll("[^a-zA-Z0-9_]", "_");
            String username = userRepository.existsByUsername(baseUsername)
                ? baseUsername + "_" + googleId.substring(0, 6)
                : baseUsername;

            user = new UserEntity(username, email, name, googleId, "ROLE_USER");
            userRepository.save(user);
            log.info("New Google user registered: username={}, email={}", username, email);
        }

        // Hamare app ka JWT token generate karo (same format as regular login)
        String token = jwtUtil.generateToken(user.getUsername());

        // Frontend pe redirect karo - token URL mein dalo
        // Frontend is token ko localStorage mein rakhega aur future requests mein use karega
        String redirectUrl = successRedirectUrl
            + "?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8)
            + "&username=" + URLEncoder.encode(user.getUsername(), StandardCharsets.UTF_8);

        response.sendRedirect(redirectUrl);
    }
}
