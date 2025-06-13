package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.Contato;
import com.exemple.maintenance_system_api.domain.model.Endereco;
import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.dto.FuncionarioCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.FuncionarioUpdateDTO;
import com.exemple.maintenance_system_api.repositories.ContatoRepository;
import com.exemple.maintenance_system_api.repositories.EnderecoRepository;
import com.exemple.maintenance_system_api.repositories.FuncionarioRepository;
import com.exemple.maintenance_system_api.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioService {

    @Autowired
    private final FuncionarioRepository funcionarioRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final EnderecoRepository enderecoRepository;
    @Autowired
    private final ContatoRepository contatoRepository;

    @Transactional
    public Funcionario criar(FuncionarioCreateDTO funcionarioCreateDTO) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioCreateDTO.nome());
        funcionario.setCargo(funcionarioCreateDTO.cargo());
        funcionario.setCpf(funcionarioCreateDTO.cpf());
        funcionario.setUsuario(usuarioRepository.save(new Usuario()));
        funcionario.setContato(contatoRepository.save(new Contato()));
        funcionario.setEndereco(enderecoRepository.save(new Endereco()));


        return funcionarioRepository.save(funcionario);

    }

    @Transactional
    public Funcionario atualizar(Long id, FuncionarioUpdateDTO funcionarioUpdateDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id).get();
        funcionario.setNome(funcionarioUpdateDTO.nome());
        funcionario.setCargo(funcionarioUpdateDTO.cargo());
        funcionario.setCpf(funcionarioUpdateDTO.cpf());
        funcionario.setEndereco(enderecoRepository.save(new Endereco()));
        funcionario.setUsuario(usuarioRepository.save(new Usuario()));
        funcionario.setContato(contatoRepository.save(new Contato()));
        return funcionarioRepository.save(funcionario);

    }

    @Transactional
    public void deletar(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).get();
        funcionarioRepository.deleteById(id);
    }

    @Transactional
    public Funcionario buscar(Long id){
        return funcionarioRepository.findById(id).get();

    }

    @Transactional
    public List<Funcionario> listar(){
        log.info("Listando todos os contatos");
        return funcionarioRepository.findAll();
    }

}
