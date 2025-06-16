package com.exemple.maintenance_system_api.services;

import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioAuthDTO;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioRegisterDTO;
import com.exemple.maintenance_system_api.excption.IdException;
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
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario registrar(UsuarioRegisterDTO usuarioRegisterDTO) {
        if (usuarioRepository.findByEmail(usuarioRegisterDTO.email()) != null) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRegisterDTO.senha());
        Usuario usuario = new Usuario(usuarioRegisterDTO.email(), encryptedPassword, usuarioRegisterDTO.role());

        return usuarioRepository.save(usuario);
    }


    @Transactional
    public Usuario atualizar(Long id, UsuarioRegisterDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IdException("Usuário não encontrado"));
        usuario.setEmail(usuarioUpdateDTO.email());
        String senhaCriptografada = passwordEncoder.encode(usuarioUpdateDTO.senha());
        usuario.setSenha(senhaCriptografada);
        usuario.setRole(usuarioUpdateDTO.role());

        return usuarioRepository.save(usuario);

    }

    @Transactional
    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }

    @Transactional
    public Usuario buscar(Long id) {
        log.info("Buscando Usuário com o id {}", id);
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public List<Usuario> listar() {
        log.info("Listando todos os Usuários");
        return usuarioRepository.findAll();
    }

}

