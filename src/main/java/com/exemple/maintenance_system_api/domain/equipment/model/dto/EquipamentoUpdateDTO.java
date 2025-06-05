package com.exemple.maintenance_system_api.domain.equipment.model.dto;


public record EquipamentoUpdateDTO(String nome, String descricao) {

    public EquipamentoUpdateDTO {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode está em branco!");
        }
    }

}
