package com.qma.userservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class JwtUtil {

    private final JwtEncoder jwtEncoder;

    @Value("${jwt.expiration}")
    private long expiration;

    public JwtUtil(JwtEncoder jwtEncoder) { this.jwtEncoder = jwtEncoder; }

    public String generateToken(String username) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("quantity-measurement-app")
                .issuedAt(now)
                .expiresAt(now.plusMillis(expiration))
                .subject(username)
                .claim("role", "ROLE_USER")
                .build();
        return jwtEncoder.encode(
                JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims)
        ).getTokenValue();
    }
}
