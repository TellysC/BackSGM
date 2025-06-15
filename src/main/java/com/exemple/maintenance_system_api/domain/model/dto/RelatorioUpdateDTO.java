package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.OrdemServico;

public record RelatorioUpdateDTO (String titulo, String descricao, OrdemServico ordemServicoId) {

}
