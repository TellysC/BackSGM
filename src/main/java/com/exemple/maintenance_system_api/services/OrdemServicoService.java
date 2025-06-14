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
        public OrdemServico criar (OrdemServicoCreateDTO ordemServicoCreateDTO) {
            OrdemServico ordemServico = new OrdemServico();

            ordemServico.setDescricao(ordemServicoCreateDTO.descricao());
            ordemServico.setStatus(ordemServicoCreateDTO.status());
            ordemServico.setFuncionario(funcionarioRepository.save(new Funcionario()));
            ordemServico.setEquipamento(equipamentoRepository.save(new Equipamento()));
            return ordemServicoRepository.save(ordemServico);

        }

        @Transactional
        public OrdemServico atualizar(Long id, OrdemServicoUpdateDTO ordemServicoUpdateDTO){
            OrdemServico ordemServico = ordemServicoRepository.findById(id).get();
            ordemServico.setDescricao(ordemServicoUpdateDTO.descricao());
            ordemServico.setStatus(ordemServicoUpdateDTO.status());
            ordemServico.setFuncionario(funcionarioRepository.save(new Funcionario()));
            ordemServico.setEquipamento(equipamentoRepository.save(new Equipamento()));
            return ordemServicoRepository.save(ordemServico);

        }

        @Transactional
        public void deletar(Long id) {
            OrdemServico ordemServico = ordemServicoRepository.findById(id).get();
            ordemServicoRepository.deleteById(id);

        }

       public OrdemServico buscar(Long id) {
            return ordemServicoRepository.findById(id).get();

       }

       public List<OrdemServico> listar() {
            log.info("Listando todas as ordens de serviço");
            return ordemServicoRepository.findAll();
       }
}