package com.exemple.maintenance_system_api.domain.equipment.model.dto;

import com.exemple.maintenance_system_api.excption.FieldException;

public record EquipamentoCreateDTO(String nome, String descricao) {

    public EquipamentoCreateDTO {
        if (nome == null || nome.isBlank()) {
            throw new FieldException("Nome não pode ser nulo");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new FieldException("Descrição não pode está em branco!");
        }
    }

}
