package com.qma.userservice.security;

import com.qma.userservice.entity.UserEntity;
import com.qma.userservice.repository.UserRepository;
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

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(GoogleOAuth2SuccessHandler.class);

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Value("${app.oauth2.success-redirect-url}")
    private String successRedirectUrl;

    public GoogleOAuth2SuccessHandler(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) auth.getPrincipal();
        String googleId = oAuth2User.getAttribute("sub");
        String email    = oAuth2User.getAttribute("email");
        String name     = oAuth2User.getAttribute("name");

        if (email == null || googleId == null) {
            res.sendRedirect(successRedirectUrl + "?error=google_auth_failed");
            return;
        }

        log.info("Google OAuth2 login: email={}", email);
        Optional<UserEntity> existing = userRepository.findByGoogleId(googleId);
        UserEntity user;

        if (existing.isPresent()) {
            user = existing.get();
        } else {
            String base = email.split("@")[0].replaceAll("[^a-zA-Z0-9_]", "_");
            String username = userRepository.existsByUsername(base)
                    ? base + "_" + googleId.substring(0, 6) : base;
            user = new UserEntity(username, email, name, googleId, "ROLE_USER");
            userRepository.save(user);
            log.info("New Google user: {}", username);
        }

        String token = jwtUtil.generateToken(user.getUsername());
        String redirectUrl = successRedirectUrl
                + "?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8)
                + "&username=" + URLEncoder.encode(user.getUsername(), StandardCharsets.UTF_8);
        res.sendRedirect(redirectUrl);
    }
}
