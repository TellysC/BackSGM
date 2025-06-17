package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.Contato;
import com.exemple.maintenance_system_api.domain.model.dto.ContatoCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.ContatoUpdateDTO;
import com.exemple.maintenance_system_api.services.CodigoDistanciaService;
import com.exemple.maintenance_system_api.services.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contato")
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping("/criar")
    public ResponseEntity<Contato> criar(@RequestBody ContatoCreateDTO contatoCreateDTO) {
        Contato contato = contatoService.criar(contatoCreateDTO);
        return ResponseEntity.status(201).body(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable long id, @RequestBody ContatoUpdateDTO contatoUpdateDTO) {
        Contato contato = contatoService.atualizar(id, contatoUpdateDTO);
        return ResponseEntity.ok(contato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@RequestBody Long id) {
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscar(@PathVariable long id) {
        Contato contato = contatoService.buscar(id);
        return ResponseEntity.ok(contato);
    }

    @GetMapping()
    public ResponseEntity<List<Contato>> listarContatos() {
        List<Contato> contatos = contatoService.listar();
        return ResponseEntity.ok(contatos);
    }
}
