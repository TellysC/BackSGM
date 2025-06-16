package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.Relatorio;
import com.exemple.maintenance_system_api.domain.model.OrdemServico;
import com.exemple.maintenance_system_api.domain.model.dto.RelatorioCreateDTO;
import com.exemple.maintenance_system_api.repositories.RelatorioRepository;
import com.exemple.maintenance_system_api.repositories.OrdemServicoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final OrdemServicoRepository ordemServicoRepository;

    @Transactional
    public Relatorio criar(RelatorioCreateDTO relatorioCreateDTO) {
        Relatorio relatorio = new Relatorio();
        relatorio.setTitulo(relatorioCreateDTO.titulo());
        relatorio.setDescricao(relatorioCreateDTO.descricao());

        OrdemServico ordem = ordemServicoRepository.findById(relatorioCreateDTO.ordemServicoId())
                .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));

        relatorio.setOrdemServico(ordem);
        return relatorioRepository.save(relatorio);
    }

    @Transactional
    public void deletar(Long id) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));
        relatorioRepository.delete(relatorio);
    }

    @Transactional
    public Relatorio buscar(Long id) {
        return relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));
    }

    public List<Relatorio> listar() {
        log.info("Listando todos os relatórios");
        return relatorioRepository.findAll();
    }
}
