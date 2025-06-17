package com.exemple.maintenance_system_api.domain.model.dto;

public record ContatoCreateDTO (String telefone, String celular, CodigoDistanciaCreateDTO codigoDistanciaCreateDTO) {
    public ContatoCreateDTO {
        if (celular == null || celular.isBlank()) {
            throw new IllegalArgumentException("Celular não pode ser nulo ou em branco");
        }
        if (codigoDistanciaCreateDTO == null) {
            throw new IllegalArgumentException("Código de distância não pode ser nulo");
        }
    }
}
