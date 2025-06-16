package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioAuthDTO;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioRegisterDTO;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioResponseDTO;
import com.exemple.maintenance_system_api.infra.security.TokenService;
import com.exemple.maintenance_system_api.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioAuthDTO usuarioAuthDTO) {
        var emailSenha = new UsernamePasswordAuthenticationToken(usuarioAuthDTO.email(), usuarioAuthDTO.senha());
        var authentication = authenticationManager.authenticate(emailSenha);

        var token = tokenService.generateToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new UsuarioResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRegisterDTO usuarioRegisterDTO) {
       if(this.usuarioRepository.findByEmail(usuarioRegisterDTO.email()) != null) return ResponseEntity.ok().build();

       String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRegisterDTO.senha());
       Usuario usuario = new Usuario(usuarioRegisterDTO.email(), encryptedPassword, usuarioRegisterDTO.role());

       this.usuarioRepository.save(usuario);
         return ResponseEntity.ok().build();

    }
}
