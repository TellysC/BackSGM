package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.OrdemServico;
import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.Equipamento;
import com.exemple.maintenance_system_api.repositories.FuncionarioRepository;
import com.exemple.maintenance_system_api.repositories.OrdemServicoRepository;
import com.exemple.maintenance_system_api.repositories.EquipamentoRepository;
import com.exemple.maintenance_system_api.domain.model.dto.OrdemServicoUpdateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.OrdemServicoCreateDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class OrdemServicoService {

    @Autowired
    private final OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    private final EquipamentoRepository equipamentoRepository;

    @Transactional
    public OrdemServico criar(OrdemServicoCreateDTO ordemServicoCreateDTO) {
        OrdemServico ordemServico = new OrdemServico();

        ordemServico.setDescricao(ordemServicoCreateDTO.descricao());
        ordemServico.setStatus(ordemServicoCreateDTO.status());
        ordemServico.setTipoManuntencao(ordemServicoCreateDTO.tipoManuntencao());
        Equipamento equipamento = equipamentoRepository.findById(ordemServicoCreateDTO.equipamento().getId())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(ordemServicoCreateDTO.funcionario().getId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        ordemServico.setEquipamento(equipamento);
        ordemServico.setFuncionario(funcionario);

        return ordemServicoRepository.save(ordemServico);
    }


    @Transactional
    public OrdemServico atualizar(Long id, OrdemServicoUpdateDTO ordemServicoUpdateDTO) {
        log.info("Atualizando funcionario com o id: {}", id);
        OrdemServico ordemServico = ordemServicoRepository.findById(id).get();
        ordemServico.setDescricao(ordemServicoUpdateDTO.descricao());
        ordemServico.setStatus(ordemServicoUpdateDTO.status());
        ordemServico.setTipoManuntencao(ordemServicoUpdateDTO.tipoManuntencao());
        Equipamento equipamento = equipamentoRepository.findById(ordemServicoUpdateDTO.equipamento().getId())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(ordemServicoUpdateDTO.funcionario().getId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        ordemServico.setEquipamento(equipamento);
        ordemServico.setFuncionario(funcionario);

        return ordemServicoRepository.save(ordemServico);

    }

    @Transactional
    public void deletar(Long id) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));
        ordemServicoRepository.delete(ordemServico);
    }

    @Transactional
    public OrdemServico buscar(Long id) {
        return ordemServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));
    }


    public List<OrdemServico> listar() {
        log.info("Listando todas as ordens de serviço");
        return ordemServicoRepository.findAll();
    }
}