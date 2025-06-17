package com.exemple.maintenance_system_api.domain.model.dto;

public record UsuarioResponseDTO(String token) {
    public UsuarioResponseDTO {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Token não pode ser nulo ou vazio");
        }
    }
}
