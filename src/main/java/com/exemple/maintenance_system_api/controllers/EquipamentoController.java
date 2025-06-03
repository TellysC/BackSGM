package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.equipment.model.Equipamento;
import com.exemple.maintenance_system_api.domain.equipment.model.dto.EquipamentoCreateDTO;
import com.exemple.maintenance_system_api.domain.equipment.model.dto.EquipamentoUpdateDTO;
import com.exemple.maintenance_system_api.services.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @PostMapping("/criar")
    public ResponseEntity<Equipamento> criarEquipamento(@RequestBody EquipamentoCreateDTO equipamentoCreateDTO) {
        Equipamento equipamento = equipamentoService.criarEquipamento(equipamentoCreateDTO);
        return ResponseEntity.status(201).body(equipamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizarEquipamento(@PathVariable long id, @RequestBody EquipamentoUpdateDTO equipamentoUpdateDTO) {
        Equipamento equipamento = equipamentoService.atualizarEquipamento(id, equipamentoUpdateDTO);
        return ResponseEntity.ok(equipamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEquipamento(@PathVariable long id) {
        equipamentoService.deletarEquipamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarEquipamentoPeloId(@PathVariable long id) {
        Equipamento equipamento = equipamentoService.buscarEquipamentoPeloId(id);
        return ResponseEntity.ok(equipamento);
    }

    @GetMapping()
    public ResponseEntity<List<Equipamento>> listarEquipamentos() {
        List<Equipamento> equipamento = equipamentoService.listarTodosOsEquipamentos();
        return ResponseEntity.ok(equipamento);
    }

}
