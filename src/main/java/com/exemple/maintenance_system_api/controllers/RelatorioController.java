package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.Relatorio;
import com.exemple.maintenance_system_api.domain.model.dto.RelatorioCreateDTO;
import com.exemple.maintenance_system_api.services.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/relatorio")
@RestController
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @PostMapping("/ordem-servico")
    public ResponseEntity<Relatorio> criar(@RequestBody RelatorioCreateDTO relatorioCreateDTO) {
        Relatorio relatorio = relatorioService.criar(relatorioCreateDTO);
        return ResponseEntity.status(201).body(relatorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relatorio> buscar(@PathVariable Long id) {
        Relatorio relatorio = relatorioService.buscar(id);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping()
    public ResponseEntity<List<Relatorio>> listar() {
        List<Relatorio> relatorios = relatorioService.listar();
        return ResponseEntity.ok(relatorios);
    }
}
