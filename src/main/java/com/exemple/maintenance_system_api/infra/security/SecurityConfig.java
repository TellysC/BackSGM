package com.exemple.maintenance_system_api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SecurityFilter securityFilter) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/funcionario", "/equipamento").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/funcionario", "/equipamento").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/funcionario", "/equipamento").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/funcionario", "/funcionario/{id}").hasAnyRole("ADMINISTRADOR", "TECNICO", "CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/ordem-servico").hasAnyRole("TECNICO", "CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/ordem-servico").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.POST, "/relatorio").hasRole("TECNICO")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
