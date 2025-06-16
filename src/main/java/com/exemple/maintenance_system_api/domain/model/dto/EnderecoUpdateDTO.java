package com.exemple.maintenance_system_api.domain.model.dto;

public record EnderecoUpdateDTO (String logradouro, String numero, String bairro, String cidade, String estado, String cep, String pais) {
}
