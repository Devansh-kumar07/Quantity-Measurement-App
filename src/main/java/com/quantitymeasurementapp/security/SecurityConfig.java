package com.quantitymeasurementapp.security;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Google OAuth2 success handler - Google login ke baad chalega
    @Autowired
    @Lazy
    private GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey getSecretKey() {
        return new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(getSecretKey()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // Public - bina token ke accessible
            		.requestMatchers("/oauth2/**", "/login/**", "/login/oauth2/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                // OAuth2 Google login endpoints - Spring automatically handle karta hai
             
                // Baaki sab protected - JWT token required
                .anyRequest().authenticated()
            )

            // =============================================
            // GOOGLE OAUTH2 LOGIN - NAYI FEATURE
            // =============================================
            // .oauth2Login() - Google login button/flow enable karta hai
            // authorizationEndpoint: /oauth2/authorize/google
            // redirectionEndpoint:   /login/oauth2/code/google
            // successHandler: GoogleOAuth2SuccessHandler - JWT banata hai aur redirect karta hai
            //
            // Login URL: GET /oauth2/authorize/google
            // Ye URL Google ke login page pe redirect karega
            .oauth2Login(oauth2 -> oauth2
                .successHandler(googleOAuth2SuccessHandler)
            )

            // STATELESS session - server session nahi rakhega
            // Note: OAuth2 login ke liye temporarily session chahiye hota hai (code exchange)
            // isliye ham STATELESS nahi kar sakte - IF_REQUIRED use karo
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // JWT Resource Server - API requests ke liye JWT validate karta hai
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.decoder(jwtDecoder()))
            )

            .authenticationProvider(authenticationProvider())

            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            );

        return http.build();
    }
}
