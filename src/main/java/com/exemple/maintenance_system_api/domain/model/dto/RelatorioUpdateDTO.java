package com.exemple.maintenance_system_api.domain.model.dto;

import com.exemple.maintenance_system_api.domain.model.OrdemServico;

public record RelatorioUpdateDTO (String titulo, String descricao, OrdemServico ordemServicoId) {
    public RelatorioUpdateDTO {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser nulo ou vazio");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }
        if (ordemServicoId == null) {
            throw new IllegalArgumentException("ID da Ordem de Serviço não pode ser nulo");
        }
    }

}
