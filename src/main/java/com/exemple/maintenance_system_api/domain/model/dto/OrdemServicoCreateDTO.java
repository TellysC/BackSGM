package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.Equipamento;
import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.enums.TipoManuntencao;


public record OrdemServicoCreateDTO(String descricao, String status, TipoManuntencao tipoManuntencao, Funcionario funcionario, Equipamento equipamento, Usuario usuario) {
}
