package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.*;
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

    private final FuncionarioRepository funcionarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final ContatoRepository contatoRepository;

    @Transactional
    public Funcionario criar(FuncionarioCreateDTO funcionarioCreateDTO) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioCreateDTO.nome());
        funcionario.setCargo(funcionarioCreateDTO.cargo());
        funcionario.setCpf(funcionarioCreateDTO.cpf());

        Usuario usuario = new Usuario();
        usuario.setEmail(funcionarioCreateDTO.usuarioCreateDTO().email());
        usuario.setSenha(funcionarioCreateDTO.usuarioCreateDTO().senha());
        funcionario.setUsuario(usuario);

        Endereco endereco = new Endereco();
        endereco.setBairro(funcionarioCreateDTO.enderecoCreateDTO().bairro());
        endereco.setCep(funcionarioCreateDTO.enderecoCreateDTO().cep());
        endereco.setCidade(funcionarioCreateDTO.enderecoCreateDTO().cidade());
        endereco.setEstado(funcionarioCreateDTO.enderecoCreateDTO().estado());
        endereco.setLogradouro(funcionarioCreateDTO.enderecoCreateDTO().logradouro());
        endereco.setPais(funcionarioCreateDTO.enderecoCreateDTO().pais());
        endereco.setNumero(funcionarioCreateDTO.enderecoCreateDTO().numero());
        funcionario.setEndereco(endereco);

        Contato contato = new Contato();
        contato.setCelular(funcionarioCreateDTO.contatoCreateDTO().celular());
        contato.setTelefone(funcionarioCreateDTO.contatoCreateDTO().telefone());
        funcionario.setContato(contato);

        CodigoDistancia codigoDistancia = new CodigoDistancia();
        contato.setCodigoDistancia(codigoDistancia);

        return funcionarioRepository.save(funcionario);
    }



    @Transactional
    public Funcionario atualizar(Long id, FuncionarioUpdateDTO funcionarioUpdateDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        funcionario.setNome(funcionarioUpdateDTO.nome());
        funcionario.setCargo(funcionarioUpdateDTO.cargo());
        funcionario.setCpf(funcionarioUpdateDTO.cpf());

        // Atualizando o usuário existente
        Usuario usuario = funcionario.getUsuario();
        usuario.setEmail(funcionarioUpdateDTO.usuarioUpdateDTO().email());
        usuario.setSenha(funcionarioUpdateDTO.usuarioUpdateDTO().senha());
        funcionario.setUsuario(usuario);

        // Atualizando o endereço existente
        Endereco endereco = funcionario.getEndereco();
        endereco.setBairro(funcionarioUpdateDTO.enderecoUpdateDTO().bairro());
        endereco.setCep(funcionarioUpdateDTO.enderecoUpdateDTO().cep());
        endereco.setCidade(funcionarioUpdateDTO.enderecoUpdateDTO().cidade());
        endereco.setEstado(funcionarioUpdateDTO.enderecoUpdateDTO().estado());
        endereco.setLogradouro(funcionarioUpdateDTO.enderecoUpdateDTO().logradouro());
        endereco.setPais(funcionarioUpdateDTO.enderecoUpdateDTO().pais());
        endereco.setNumero(funcionarioUpdateDTO.enderecoUpdateDTO().numero());
        funcionario.setEndereco(endereco);

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
