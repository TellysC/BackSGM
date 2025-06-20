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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Funcionario criar(FuncionarioCreateDTO funcionarioCreateDTO) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(funcionarioCreateDTO.nome());
        funcionario.setCpf(funcionarioCreateDTO.cpf());

        Usuario usuario = new Usuario();
        usuario.setEmail(funcionarioCreateDTO.usuarioRegisterDTO().email());
        String senhaCriptografada = passwordEncoder.encode(funcionarioCreateDTO.usuarioRegisterDTO().senha());
        usuario.setSenha(senhaCriptografada);
        usuario.setRole(funcionarioCreateDTO.usuarioRegisterDTO().role());
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

        CodigoDistancia codigoDistancia = new CodigoDistancia();
        codigoDistancia.setEstado(funcionarioCreateDTO.contatoCreateDTO().codigoDistanciaCreateDTO().estado());
        codigoDistancia.setNumero(funcionarioCreateDTO.contatoCreateDTO().codigoDistanciaCreateDTO().numero());
        contato.setCodigoDistancia(codigoDistancia);
        funcionario.setContato(contato);

        return funcionarioRepository.save(funcionario);
    }



    @Transactional
    public Funcionario atualizar(Long id, FuncionarioUpdateDTO funcionarioUpdateDTO) {
        log.info("Atualizando funcionario com o id: {}", id);
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        funcionario.setNome(funcionarioUpdateDTO.nome());
        funcionario.setCpf(funcionarioUpdateDTO.cpf());
        funcionario.getUsuario().setEmail(funcionarioUpdateDTO.usuarioRegisterDTO().email());
        funcionario.getUsuario().setSenha(funcionarioUpdateDTO.usuarioRegisterDTO().senha());
        funcionario.getUsuario().setRole(funcionarioUpdateDTO.usuarioRegisterDTO().role());
        funcionario.getEndereco().setBairro(funcionarioUpdateDTO.enderecoUpdateDTO().bairro());
        funcionario.getEndereco().setCep(funcionarioUpdateDTO.enderecoUpdateDTO().cep());
        funcionario.getEndereco().setCidade(funcionarioUpdateDTO.enderecoUpdateDTO().cidade());
        funcionario.getEndereco().setEstado(funcionarioUpdateDTO.enderecoUpdateDTO().estado());
        funcionario.getEndereco().setLogradouro(funcionarioUpdateDTO.enderecoUpdateDTO().logradouro());
        funcionario.getEndereco().setPais(funcionarioUpdateDTO.enderecoUpdateDTO().pais());
        funcionario.getEndereco().setNumero(funcionarioUpdateDTO.enderecoUpdateDTO().numero());
        funcionario.getContato().setCelular(funcionarioUpdateDTO.contatoUpdateDTO().celular());
        funcionario.getContato().setTelefone(funcionarioUpdateDTO.contatoUpdateDTO().telefone());
        funcionario.getContato().getCodigoDistancia().setEstado(funcionarioUpdateDTO.contatoUpdateDTO().codigoDistanciaUpdateDTO().estado());
        funcionario.getContato().getCodigoDistancia().setNumero(funcionarioUpdateDTO.contatoUpdateDTO().codigoDistanciaUpdateDTO().numero());
        log.info("Atualizando funcionário com ID: {}", id);
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void deletar(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        funcionarioRepository.delete(funcionario);
    }

    @Transactional
    public Funcionario buscar(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    @Transactional
    public List<Funcionario> listar() {
        log.info("Listando todos os funcionários");
        return funcionarioRepository.findAll();
    }

}