package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.OrdemServico;
import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.Equipamento;
import com.exemple.maintenance_system_api.domain.model.enums.StatusOrdemServico;
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
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
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
    public OrdemServico fechar(Long id) {
        OrdemServico ordem = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));

        if (ordem.getStatus() == StatusOrdemServico.CONCLUIDA) {
            throw new RuntimeException("Ordem já está concluída");
        }

        ordem.setStatus(StatusOrdemServico.CONCLUIDA);
        return ordemServicoRepository.save(ordem);
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

    public List<OrdemServico> listarAbertas() {
        return ordemServicoRepository.findByStatus(StatusOrdemServico.ABERTA);
    }

    // Métodos que buscam por funcionarioId (utilizados internamente pelos métodos *DoUsuario)
    @Transactional
    public List<OrdemServico> listarPorFuncionario(Long funcionarioId) {
        log.info("Listando ordens de serviço para o funcionário com ID: {}", funcionarioId);
        List<OrdemServico> ordens = ordemServicoRepository.findByFuncionarioId(funcionarioId);
        log.debug("Repositorio retornou {} ordens para o funcionario ID: {}", ordens.size(), funcionarioId);
        return ordens;
    }

    @Transactional
    public List<OrdemServico> listarAbertasPorFuncionario(Long funcionarioId) {
        log.info("Listando ordens de serviço abertas para o funcionário com ID: {}", funcionarioId);
        List<OrdemServico> ordens = ordemServicoRepository.findByFuncionarioIdAndStatus(funcionarioId, StatusOrdemServico.ABERTA);
        log.debug("Repositorio retornou {} ordens ABERTAS para o funcionario ID: {}", ordens.size(), funcionarioId);
        return ordens;
    }

    @Transactional
    public List<OrdemServico> listarConcluidasPorFuncionario(Long funcionarioId) {
        log.info("Listando ordens de serviço concluídas para o funcionário com ID: {}", funcionarioId);
        List<OrdemServico> ordens = ordemServicoRepository.findByFuncionarioIdAndStatus(funcionarioId, StatusOrdemServico.CONCLUIDA);
        log.debug("Repositorio retornou {} ordens CONCLUIDAS para o funcionario ID: {}", ordens.size(), funcionarioId);
        return ordens;
    }

    // NOVOS MÉTODOS que o Controller espera e que resolvem o FuncionarioId a partir do UsuarioId
    @Transactional
    public List<OrdemServico> listarPorFuncionarioDoUsuario(Long usuarioId) {
        Funcionario funcionario = funcionarioRepository.findByUsuarioId(usuarioId);
        if (funcionario == null) {
            log.warn("Nenhum funcionário encontrado para o usuário com ID: {}", usuarioId);
            return List.of();
        }
        log.info("Buscando ordens para o funcionário do usuário ID: {} (Funcionario ID: {})", usuarioId, funcionario.getId());
        // Chama o método existente que já faz o log de debug do repositório
        return this.listarPorFuncionario(funcionario.getId());
    }

    @Transactional
    public List<OrdemServico> listarAbertasPorFuncionarioDoUsuario(Long usuarioId) {
        Funcionario funcionario = funcionarioRepository.findByUsuarioId(usuarioId);
        if (funcionario == null) {
            log.warn("Nenhum funcionário encontrado para o usuário com ID: {}", usuarioId);
            return List.of();
        }
        log.info("Buscando ordens ABERTAS para o funcionário do usuário ID: {} (Funcionario ID: {})", usuarioId, funcionario.getId());
        return this.listarAbertasPorFuncionario(funcionario.getId());
    }

    @Transactional
    public List<OrdemServico> listarConcluidasPorFuncionarioDoUsuario(Long usuarioId) {
        Funcionario funcionario = funcionarioRepository.findByUsuarioId(usuarioId);
        if (funcionario == null) {
            log.warn("Nenhum funcionário encontrado para o usuário com ID: {}", usuarioId);
            return List.of();
        }
        log.info("Buscando ordens CONCLUIDAS para o funcionário do usuário ID: {} (Funcionario ID: {})", usuarioId, funcionario.getId());
        return this.listarConcluidasPorFuncionario(funcionario.getId());
    }
}