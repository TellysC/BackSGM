package com.exemple.maintenance_system_api.domain.model.dto;

public record UsuarioAuthDTO(String email, String senha) {
    public UsuarioAuthDTO {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha cannot be null or blank");
        }
    }
}
