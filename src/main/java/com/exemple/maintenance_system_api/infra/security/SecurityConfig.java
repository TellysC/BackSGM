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

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()

                        // Endpoints específicos para ADMINISTRADOR
                        .requestMatchers(HttpMethod.POST, "/funcionario/criar", "/equipamento/criar").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.DELETE, "/funcionario/{id}", "/equipamento/{id}").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.PUT, "/funcionario/{id}", "/equipamento/{id}").hasRole("ADMINISTRADOR")

                        // Endpoints específicos para CLIENTE e TÉCNICO (agora TÉCNICO também pode acessar suas ordens)
                        .requestMatchers(HttpMethod.POST, "/ordem-servico/criar").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/ordem-servico/minhas-criadas", "/ordem-servico/minhas-abertas", "/ordem-servico/minhas-concluidas").hasAnyRole("CLIENTE", "TECNICO") // ADICIONADO: TECNICO pode ver suas ordens


                        // Endpoints específicos para TÉCNICO e ADMINISTRADOR (visão geral/gerenciamento)
                        .requestMatchers(HttpMethod.GET, "/ordem-servico/{id}", "/ordem-servico", "/ordem-servico/abertas").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.POST, "/relatorio/ordem-servico").hasRole("TECNICO")
                        .requestMatchers(HttpMethod.PUT, "/ordem-servico/fechar").hasAnyRole("TECNICO", "ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET, "/relatorio/{id}", "/relatorio").hasAnyRole("TECNICO", "ADMINISTRADOR")

                        // Permissão para USUÁRIOS (CLIENTE, TECNICO, ADM) listar equipamentos e usuários (para contagem)
                        .requestMatchers(HttpMethod.GET, "/equipamento").authenticated() // Já permitia
                        .requestMatchers(HttpMethod.GET, "/usuario").authenticated() // NOVO: Permitir a usuários autenticados listar usuários (para contagem)


                        // Qualquer outra requisição deve ser autenticada
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de segurança customizado
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Aplica a configuração CORS
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

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}