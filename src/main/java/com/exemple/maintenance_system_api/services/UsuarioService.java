package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioUpdateDTO;
import com.exemple.maintenance_system_api.excption.IdException;
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
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario criar(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCreateDTO.email());
        usuario.setSenha(usuarioCreateDTO.senha());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IdException("Usuário não encontrado"));
        usuario.setEmail(usuarioUpdateDTO.email());
        usuario.setSenha(usuarioUpdateDTO.senha());
        return usuarioRepository.save(usuario);

    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario buscar(Long id) {
        log.info("Buscando Usuario com o id {}", id);
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Usuario> listar() {
        log.info("Listando todas as Usuarios");
        return usuarioRepository.findAll();
    }
}
