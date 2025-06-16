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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Removido o securityFilter daqui para usar o @Autowired da classe
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        // Endpoints de Administrador
                        .requestMatchers("/funcionario/**").hasRole("ADMINISTRADOR")
                        .requestMatchers("/equipamento/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/relatorio/**").hasRole("ADMINISTRADOR") // Admin pode ver todos relatórios

                        // Endpoints de Técnico
                        .requestMatchers(HttpMethod.POST, "/relatorio/ordem-servico").hasRole("TECNICO")
                        .requestMatchers(HttpMethod.PUT, "/ordem-servico/{id}/fechar").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/ordem-servico/abertas").hasAnyRole("TECNICO", "ADMINISTRADOR")

                        // Endpoints de Cliente
                        .requestMatchers(HttpMethod.POST, "/ordem-servico/criar").hasRole("CLIENTE")

                        // Qualquer outra requisição precisa estar autenticada
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // O bean do CorsFilter não é mais necessário aqui se você usar @CrossOrigin nos controllers,
    // mas se quiser uma configuração central, mantenha-o e remova os @CrossOrigin individuais.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}