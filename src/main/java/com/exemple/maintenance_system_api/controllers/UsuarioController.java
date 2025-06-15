package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.Usuario;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioAuthDTO;
import com.exemple.maintenance_system_api.domain.model.dto.UsuarioRegisterDTO;
import com.exemple.maintenance_system_api.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioAuthDTO usuarioCreateDTO) {
        Usuario usuario = usuarioService.criar(usuarioCreateDTO);
        return ResponseEntity.status(201).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody UsuarioRegisterDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioService.atualizar(id, usuarioUpdateDTO);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscar(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }
}
