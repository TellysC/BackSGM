package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.CodigoDistancia;
import com.exemple.maintenance_system_api.domain.model.dto.CodigoDistanciaCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.CodigoDistanciaUpdateDTO;
import com.exemple.maintenance_system_api.excption.IdException;
import com.exemple.maintenance_system_api.repositories.CodigoDistanciaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CodigoDistanciaService {

    @Autowired
    private final CodigoDistanciaRepository codigoDistanciaRepository;

    @Transactional
    public CodigoDistancia criar(CodigoDistanciaCreateDTO codigoDistanciaCreateDTO) {
        CodigoDistancia codigoDistancia = new CodigoDistancia();

        codigoDistancia.setEstado(codigoDistanciaCreateDTO.estado());
        codigoDistancia.setNumero(codigoDistanciaCreateDTO.numero());

        log.info("Criando código de distância com estado: {} e número: {}", codigoDistanciaCreateDTO.estado(), codigoDistanciaCreateDTO.numero());

        return codigoDistanciaRepository.save(codigoDistancia);
    }

    @Transactional
    public CodigoDistancia atualizar(long id, CodigoDistanciaUpdateDTO codigoDistanciaUpdateDTO) {
        CodigoDistancia codigoDistancia = codigoDistanciaRepository.findById(id).orElseThrow(() -> new IdException("Equipamento não encontrado"));
        codigoDistancia.setEstado(codigoDistanciaUpdateDTO.estado());
        codigoDistancia.setNumero(codigoDistanciaUpdateDTO.numero());

        return codigoDistanciaRepository.save(codigoDistancia);

    }

    @Transactional
    public void deletar(Long id) {
        CodigoDistancia codigo = codigoDistanciaRepository.findById(id).orElseThrow(() -> new IdException("Código Distancia não encontrado"));
        log.info("Deletando Código Distancia com o id {}", id);
        codigoDistanciaRepository.delete(codigo);
    }

    @Transactional
    public CodigoDistancia buscar(Long id) {
        log.info("Buscando Código Distancia com o id {}", id);
        return codigoDistanciaRepository.findById(id).orElseThrow(() -> new IdException("Código Distancia não encontrado"));
    }

    @Transactional
    public List<CodigoDistancia> listar() {
        log.info("Listando todos os Códigos Distancia");
        return codigoDistanciaRepository.findAll();
    }
}
