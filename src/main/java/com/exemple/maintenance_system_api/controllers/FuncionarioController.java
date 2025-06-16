package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.Funcionario;
import com.exemple.maintenance_system_api.domain.model.dto.FuncionarioCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.FuncionarioUpdateDTO;
import com.exemple.maintenance_system_api.services.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private final FuncionarioService funcionarioService;

    @PostMapping("/criar")
    public ResponseEntity<Funcionario> criar(@RequestBody FuncionarioCreateDTO funcionarioCreateDTO) {
        Funcionario funcionario = funcionarioService.criar(funcionarioCreateDTO);
        return ResponseEntity.status(201).body(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id,@RequestBody FuncionarioUpdateDTO funcionarioUpdateDTO) {
        Funcionario funcionario = funcionarioService.atualizar(id, funcionarioUpdateDTO);
        return ResponseEntity.ok(funcionario);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@RequestBody Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscar(@PathVariable long id) {
        Funcionario funcionario = funcionarioService.buscar(id);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping()
    public ResponseEntity<List<Funcionario>> listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listar();
        return ResponseEntity.ok(funcionarios);
    }
}

