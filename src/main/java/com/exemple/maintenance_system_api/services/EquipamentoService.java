package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.Equipamento;
import com.exemple.maintenance_system_api.domain.model.dto.EquipamentoCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.EquipamentoUpdateDTO;
import com.exemple.maintenance_system_api.excption.IdException;
import com.exemple.maintenance_system_api.repositories.EquipamentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    @Transactional
    public Equipamento criarEquipamento(EquipamentoCreateDTO equipamentoCreateDTO) {
        Equipamento equipamento = new Equipamento();

        equipamento.setNome(equipamentoCreateDTO.nome());
        equipamento.setDescricao(equipamentoCreateDTO.descricao());

        log.info("Criando equipamento com o nome: {} e descrição: {}", equipamento.getNome(), equipamento.getDescricao());
        return equipamentoRepository.save(equipamento);
    }

    @Transactional
    public Equipamento atualizarEquipamento(Long id, EquipamentoUpdateDTO equipamentoUpdateDTO) {
        log.info("Atualizando equipamento com o id: {}", id);
        Equipamento equipamento = equipamentoRepository.findById(id).orElseThrow(() -> new IdException("Equipamento não encontrado"));
        equipamento.setNome(equipamentoUpdateDTO.nome());
        equipamento.setDescricao(equipamentoUpdateDTO.descricao());
        log.info("Atualizando equipamento: {}", equipamento);
        return equipamentoRepository.save(equipamento);
    }

    @Transactional
    public void deletarEquipamento(Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new IdException("Equipamento não encontrado"));
        log.info("Deletando equipamento com o id {}", id);
        equipamentoRepository.delete(equipamento);
    }

    @Transactional
    public Equipamento buscarEquipamentoPeloId(Long id) {
        log.info("Buscando equipamento com o id {}", id);
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new IdException("Equipamento não encontrado"));
    }

    @Transactional
    public List<Equipamento> listarTodosOsEquipamentos() {
        log.info("Listando todos os equipamentos");
        return equipamentoRepository.findAll();
    }
}