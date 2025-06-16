package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.OrdemServico;
import com.exemple.maintenance_system_api.domain.model.dto.OrdemServicoCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.OrdemServicoUpdateDTO;
import com.exemple.maintenance_system_api.services.OrdemServicoService;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ordem-servico")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor

public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    @PostMapping("/criar")
    public ResponseEntity<OrdemServico> criar(@RequestBody OrdemServicoCreateDTO ordemServicoCreateDTO) {
        OrdemServico ordemServico = ordemServicoService.criar(ordemServicoCreateDTO);
        return ResponseEntity.status(201).body(ordemServico);

    }

    @PutMapping("/{id}/fechar")
    public ResponseEntity<OrdemServico> fechar(@PathVariable Long id) {
        OrdemServico ordemFechada = ordemServicoService.fechar(id);
        return ResponseEntity.ok(ordemFechada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<OrdemServico> deletar(@RequestBody Long id) {
        ordemServicoService.deletar(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long id) {
        OrdemServico ordemServico = ordemServicoService.buscar(id);
        return ResponseEntity.ok(ordemServico);

    }

    @GetMapping()
    public ResponseEntity<List<OrdemServico>> listar(){
        List<OrdemServico> ordemServico = ordemServicoService.listar();
        return ResponseEntity.ok(ordemServico);
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<OrdemServico>> listarAbertas() {
        List<OrdemServico> abertas = ordemServicoService.listarAbertas();
        return ResponseEntity.ok(abertas);
    }

}

