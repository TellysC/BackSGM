package com.exemple.maintenance_system_api.domain.model.dto;

public record EnderecoCreateDTO (String logradouro, String numero, String bairro, String cidade, String estado, String cep, String pais) {

    public EnderecoCreateDTO {
        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro não pode ser nulo ou em branco");
        }
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Número não pode ser nulo ou em branco");
        }
        if (bairro == null || bairro.isBlank()) {
            throw new IllegalArgumentException("Bairro não pode ser nulo ou em branco");
        }
        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade não pode ser nula ou em branco");
        }
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Estado não pode ser nulo ou em branco");
        }
        if (cep == null || cep.isBlank()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou em branco");
        }
        if (pais == null || pais.isBlank()) {
            throw new IllegalArgumentException("País não pode ser nulo ou em branco");
        }
    }

}
