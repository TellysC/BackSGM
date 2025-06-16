package com.exemple.maintenance_system_api.domain.model.dto;

public record UsuarioAuthDTO(String email, String senha) {
    public UsuarioAuthDTO {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
    }
}
