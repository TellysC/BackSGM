package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.Endereco;
import com.exemple.maintenance_system_api.domain.model.dto.EnderecoCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.EnderecoUpdateDTO;
import com.exemple.maintenance_system_api.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnderecoService {
    
    @Autowired
    private final EnderecoRepository enderecoRepository;
    
    @Transactional
    public Endereco criar(EnderecoCreateDTO enderecoCreateDTO) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoCreateDTO.logradouro());
        endereco.setNumero(enderecoCreateDTO.numero());
        endereco.setBairro(enderecoCreateDTO.bairro());
        endereco.setCidade(enderecoCreateDTO.cidade());
        endereco.setEstado(enderecoCreateDTO.estado());
        endereco.setCep(enderecoCreateDTO.cep());
        endereco.setPais(enderecoCreateDTO.pais());
        return enderecoRepository.save(endereco);
    }
    
    @Transactional
    public Endereco atualizar(Long id, EnderecoUpdateDTO enderecoUpdateDTO) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        endereco.setLogradouro(enderecoUpdateDTO.logradouro());
        endereco.setNumero(enderecoUpdateDTO.numero());
        endereco.setBairro(enderecoUpdateDTO.bairro());
        endereco.setCidade(enderecoUpdateDTO.cidade());
        endereco.setEstado(enderecoUpdateDTO.estado());
        endereco.setCep(enderecoUpdateDTO.cep());
        endereco.setPais(enderecoUpdateDTO.pais());
        return enderecoRepository.save(endereco);
    }
    
    @Transactional
    public void deletar(Long id) {
        enderecoRepository.deleteById(id);
    }
    
    @Transactional
    public Endereco buscar(Long id) {
        return enderecoRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }
}
