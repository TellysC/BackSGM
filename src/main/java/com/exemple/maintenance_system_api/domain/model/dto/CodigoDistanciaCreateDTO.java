package com.exemple.maintenance_system_api.domain.model.dto;


public record CodigoDistanciaCreateDTO(String numero, String estado) {

    public CodigoDistanciaCreateDTO {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número não pode ser nulo ou em branco");
        }
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Estado não pode ser nulo ou em branco");
        }
    }



}
