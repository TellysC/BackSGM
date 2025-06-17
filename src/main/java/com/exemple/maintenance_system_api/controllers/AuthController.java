package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioAuthDTO;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioRegisterDTO;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioResponseDTO;
import com.exemple.maintenance_system_api.infra.security.TokenService;
import com.exemple.maintenance_system_api.repositories.UsuarioRepository;
import com.exemple.maintenance_system_api.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioAuthDTO usuarioAuthDTO) {
        var emailSenha = new UsernamePasswordAuthenticationToken(usuarioAuthDTO.email(), usuarioAuthDTO.senha());
        var authentication = authenticationManager.authenticate(emailSenha);
        var token = tokenService.generateToken((Usuario) authentication.getPrincipal());
        log.info("Usuário {} autenticado com sucesso", usuarioAuthDTO.email());

        return ResponseEntity.ok(new UsuarioResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody @Valid UsuarioRegisterDTO usuarioRegisterDTO) {
        Usuario usuarioCriado = usuarioService.registrar(usuarioRegisterDTO);
        return ResponseEntity.status(201).body(usuarioCriado);
    }

}
