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
                        .requestMatchers(HttpMethod.POST, "/funcionario/criar", "/equipamento/criar").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/funcionario/{id}", "/equipamento/{id}").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/funcionario/{id}", "/equipamento/{id}").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/ordemServico/criar").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/ordemServico/{id}","/ordem-servico").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "relatorio/ordem-servico").hasRole("TECNICO")
                        .requestMatchers(HttpMethod.GET, "/relatorio/{id}", "/relatorio").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .anyRequest().authenticated())
                        .anyRequest().permitAll())
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
