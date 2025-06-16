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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SecurityFilter securityFilter) throws Exception {
        return http
                .cors(cors -> {}) // ✅ ATIVA o suporte a CORS
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login","/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/funcionario/criar", "/equipamento/criar").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/funcionario/{id}", "/equipamento/{id}").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/funcionario/{id}", "/equipamento/{id}").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/ordem-servico/criar").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/ordem-servico/{id}","/ordem-servico", "ordem-servico/abertas").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "relatorio/ordem-servico").hasRole("TECNICO")
                        .requestMatchers(HttpMethod.PUT, "ordem-servico/fechar").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/relatorio/{id}", "/relatorio").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://127.0.0.1:5500"); // Ou use "*" no dev
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
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
