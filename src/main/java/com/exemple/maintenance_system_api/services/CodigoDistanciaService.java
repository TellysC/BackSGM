package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.CodigoDistancia;
import com.exemple.maintenance_system_api.domain.model.dto.CodigoDistanciaCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.CodigoDistanciaUpdateDTO;
import com.exemple.maintenance_system_api.excption.IdException;
import com.exemple.maintenance_system_api.repositories.CodigoDistanciaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CodigoDistanciaService {

    private final CodigoDistanciaRepository codigoDistanciaRepository;

    @Transactional
    public CodigoDistancia criar(CodigoDistanciaCreateDTO dto) {
        CodigoDistancia codigoDistancia = new CodigoDistancia();

        codigoDistancia.setEstado(dto.estado());
        codigoDistancia.setNumero(dto.numero());

        log.info("Criando código de distância com estado: {} e número: {}", dto.estado(), dto.numero());

        return codigoDistanciaRepository.save(codigoDistancia);
    }

    @Transactional
    public CodigoDistancia atualizar(long id, CodigoDistanciaUpdateDTO dto) {
        CodigoDistancia codigoDistancia = codigoDistanciaRepository.findById(id).orElseThrow(() -> new IdException("Equipamento não encontrado"));
        codigoDistancia.setEstado(dto.estado());
        codigoDistancia.setNumero(dto.numero());

        return codigoDistanciaRepository.save(codigoDistancia);

    }

    public void deletar(Long id) {
        if(!codigoDistanciaRepository.existsById(id)) {
            log.error("Equipamento não encontrado com o id {}", id);
            throw new IdException("Equipamento não encontrado");
        }
        log.info("Deleting budget with id {}", id);
        codigoDistanciaRepository.deleteById(id);
    }

    public CodigoDistancia buscar(Long id) {
        log.info("Buscando equipamento com o id {}", id);
        return codigoDistanciaRepository.findById(id).orElseThrow(() -> new IdException("Equipamento não encontrado"));
    }

    public List<CodigoDistancia> listar() {
        log.info("Listando todos os equipamentos");
        return codigoDistanciaRepository.findAll();
    }
}
