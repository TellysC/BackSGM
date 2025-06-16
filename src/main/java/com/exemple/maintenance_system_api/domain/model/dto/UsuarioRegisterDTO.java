package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.enums.UsuarioRole;

public record UsuarioRegisterDTO(String email, String senha, UsuarioRole role) {

}
