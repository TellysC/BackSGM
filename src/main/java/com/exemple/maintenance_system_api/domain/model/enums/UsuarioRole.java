package com.exemple.maintenance_system_api.domain.model.enums;

public enum UsuarioRole {
    ADMINISTRADOR("Administrador"),
    TECNICO("Técnico"),
    CLIENTE("Cliente");

    private String role;

    UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
