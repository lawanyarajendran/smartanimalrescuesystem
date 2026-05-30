package com.animalrescue.smartrescue.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // HTML Pages
                        .requestMatchers(
                                "/",
                                "/*.html",
                                "/login.html",
                                "/register.html",
                                "/dashboard.html",
                                "/report.html",
                                "/viewReports.html"
                        ).permitAll()

                        // Static files
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Auth APIs
                        .requestMatchers("/auth/**")
                        .permitAll()

                        // Report APIs
                        .requestMatchers(HttpMethod.GET, "/reports")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST, "/report")
                        .permitAll()

                        .requestMatchers(HttpMethod.PUT, "/report/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/report/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/report/**")
                        .permitAll()

                        // Everything else
                        .anyRequest()
                        .authenticated()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}