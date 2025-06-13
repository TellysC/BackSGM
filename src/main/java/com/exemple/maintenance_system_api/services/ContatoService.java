package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.CodigoDistancia;
import com.exemple.maintenance_system_api.domain.model.Contato;
import com.exemple.maintenance_system_api.domain.model.dto.CodigoDistanciaCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.ContatoCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.ContatoUpdateDTO;
import com.exemple.maintenance_system_api.excption.IdException;
import com.exemple.maintenance_system_api.repositories.CodigoDistanciaRepository;
import com.exemple.maintenance_system_api.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private CodigoDistanciaRepository codigoDistanciaRepository;

    @Transactional
    public Contato criar(ContatoCreateDTO contatoCreateDTO){
        Contato contato = new Contato();

        contato.setCelular(contatoCreateDTO.celular());
        contato.setTelefone(contatoCreateDTO.telefone());
        contato.setCodigoDistancia(codigoDistanciaRepository.save(new CodigoDistancia()));

        log.info("Criando contato com celular: {} e telefone: {}", contatoCreateDTO.celular(), contatoCreateDTO.telefone());

        return contatoRepository.save(contato);
    }

    @Transactional
    public Contato atualizar(long id,ContatoUpdateDTO contatoUpdateDTO){
        Contato contato = contatoRepository.findById(id).get();
        contato.setCelular(contatoUpdateDTO.celular());
        contato.setTelefone(contatoUpdateDTO.telefone());
        log.info("Atualizando contato com celular: {} e telefone: {}", contatoUpdateDTO.celular(), contatoUpdateDTO.telefone());
        return contatoRepository.save(contato);
    }

    public void deletar(long id){
        Contato contato = contatoRepository.findById(id).get();
        contatoRepository.deleteById(id);
    }

    public Contato buscar(long id){
        return contatoRepository.findById(id).get();

    }

    public List<Contato> listar(){
        log.info("Listando todos os contatos");
        return contatoRepository.findAll();
    }
}
