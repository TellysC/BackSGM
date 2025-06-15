package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.Equipamento;
import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.Usuario;


public record OrdemServicoUpdateDTO(String descricao, String status, String tipoManuntencao, Funcionario funcionario, Equipamento equipamento, Usuario usuario) {
}
