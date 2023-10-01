package com.project.to_do_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define a PasswordEncoder bean for encoding and verifying passwords.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define a SecurityFilterChain bean to configure security settings.
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable Cross-Site Request Forgery (CSRF) protection.
        http.csrf(csrf -> csrf.disable());

        // Return the configured HttpSecurity object as a SecurityFilterChain.
        return http.build();
    }
}
