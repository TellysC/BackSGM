package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.Equipamento;
import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.enums.TipoManuntencao;


public record OrdemServicoUpdateDTO(String descricao, String status, TipoManuntencao tipoManuntencao, Funcionario funcionario, Equipamento equipamento, Usuario usuario) {
    public OrdemServicoUpdateDTO {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status não pode ser nulo ou vazio");
        }
        if (tipoManuntencao == null) {
            throw new IllegalArgumentException("Tipo de manutenção não pode ser nulo");
        }
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo");
        }
        if (equipamento == null) {
            throw new IllegalArgumentException("Equipamento não pode ser nulo");
        }
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
    }
}
