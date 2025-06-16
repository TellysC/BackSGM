package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.Endereco;
import com.exemple.maintenance_system_api.domain.model.dto.EnderecoCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.EnderecoUpdateDTO;
import com.exemple.maintenance_system_api.services.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/{criar}")
    public ResponseEntity<Endereco> criar(@RequestBody EnderecoCreateDTO enderecoCreateDTO) {
        Endereco endereco = enderecoService.criar(enderecoCreateDTO);
        return ResponseEntity.status(201).body(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @RequestBody EnderecoUpdateDTO enderecoUpdateDTO) {
        Endereco endereco = enderecoService.atualizar(id, enderecoUpdateDTO);
        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco>deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        Endereco endereco = enderecoService.buscar(id);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping()
    public ResponseEntity<List<Endereco>> listar() {
        List<Endereco> enderecos = enderecoService.listar();
        return ResponseEntity.ok(enderecos);
    }
}
