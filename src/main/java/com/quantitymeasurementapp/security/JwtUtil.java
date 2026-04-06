package com.quantitymeasurementapp.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;

// JwtUtil - Ab Spring ka JwtEncoder use karta hai
// Pehle: manually Jwts.builder() likhte the (jjwt library)
// Ab:    Spring OAuth2 ka JwtEncoder use karo - ye OAuth2 standard follow karta hai
@Component
public class JwtUtil {

    // Spring ka JwtEncoder - SecurityConfig mein @Bean ke through inject hoga
    // Ye RSA ya HMAC key use karke token sign karta hai
    private final JwtEncoder jwtEncoder;

    @Value("${jwt.expiration}")
    private long expiration; // 86400000 ms = 24 hours

    // Constructor injection - field injection se better practice
    public JwtUtil(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    // =============================================
    // TOKEN GENERATE KARO
    // =============================================
    public String generateToken(String username) {

        Instant now = Instant.now();

        // JwtClaimsSet = JWT ke andar jo data store hoga (Payload)
        JwtClaimsSet claims = JwtClaimsSet.builder()
                // issuer = token kisne banaya ("our-app")
                .issuer("quantity-measurement-app")
                // issuedAt = token kab bana
                .issuedAt(now)
                // expiresAt = token kab expire hoga
                .expiresAt(now.plusMillis(expiration))
                // subject = token kis user ka hai
                .subject(username)
                // claim = extra data daal sakte ho
                .claim("role", "ROLE_USER")
                .build();

        // JwtEncoderParameters mein claims wrap karo
        // jwtEncoder.encode() → signed JWT string return karta hai
        return jwtEncoder.encode(
        	    JwtEncoderParameters.from(
        	        JwsHeader.with(MacAlgorithm.HS256).build(), 
        	        claims
        	    )
        	).getTokenValue();
        // Result: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWh1bCJ9.abc123"
    }
}
