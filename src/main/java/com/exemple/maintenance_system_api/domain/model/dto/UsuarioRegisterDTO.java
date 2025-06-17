package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.enums.UsuarioRole;

public record UsuarioRegisterDTO(String email, String senha, UsuarioRole role) {
    public UsuarioRegisterDTO {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role não pode ser nula");
        }
    }

}
