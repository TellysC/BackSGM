package com.exemple.maintenance_system_api.domain.model.dto;

public record EnderecoCreateDTO (String logradouro, int numero, String bairro, String cidade, String estado, int cep, String pais) {
}
