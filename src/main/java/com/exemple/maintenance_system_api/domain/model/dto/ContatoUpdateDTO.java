package com.exemple.maintenance_system_api.domain.model.dto;

public record ContatoUpdateDTO (String telefone, String celular, CodigoDistanciaUpdateDTO codigoDistanciaUpdateDTO) {
    public ContatoUpdateDTO {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou em branco");
        }
        if (celular == null || celular.isBlank()) {
            throw new IllegalArgumentException("Celular não pode ser nulo ou em branco");
        }
        if (codigoDistanciaUpdateDTO == null) {
            throw new IllegalArgumentException("Código de distância não pode ser nulo");
        }
    }
}

