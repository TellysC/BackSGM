package com.exemple.maintenance_system_api.domain.model.dto;

public record ContatoCreateDTO (String telefone, String celular,Long fkCodigoDistanciaId, CodigoDistanciaCreateDTO codigoDistanciaCreateDTO) {
}
