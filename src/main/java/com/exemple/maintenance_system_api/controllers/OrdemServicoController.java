package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.OrdemServico;
import com.exemple.maintenance_system_api.domain.model.dto.OrdemServicoCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.OrdemServicoUpdateDTO;
import com.exemple.maintenance_system_api.services.OrdemServicoService;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication; //
import org.springframework.security.core.context.SecurityContextHolder; //
import com.exemple.maintenance_system_api.domain.model.Usuario; //

import java.util.List;

@RequestMapping("/ordem-servico")
@RestController
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

    @GetMapping("/minhas-criadas")
    public ResponseEntity<List<OrdemServico>> listarMinhasOrdensCriadas() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        // Chama o NOVO método do serviço que resolve o FuncionarioId a partir do UsuarioId
        List<OrdemServico> minhasOrdens = ordemServicoService.listarPorFuncionarioDoUsuario(usuarioLogado.getId());
        return ResponseEntity.ok(minhasOrdens);
    }

    @GetMapping("/minhas-abertas")
    public ResponseEntity<List<OrdemServico>> listarMinhasOrdensAbertas() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        // Chama o NOVO método do serviço
        List<OrdemServico> minhasOrdensAbertas = ordemServicoService.listarAbertasPorFuncionarioDoUsuario(usuarioLogado.getId());
        return ResponseEntity.ok(minhasOrdensAbertas);
    }

    @GetMapping("/minhas-concluidas")
    public ResponseEntity<List<OrdemServico>> listarMinhasOrdensConcluidas() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        // Chama o NOVO método do serviço
        List<OrdemServico> minhasOrdensConcluidas = ordemServicoService.listarConcluidasPorFuncionarioDoUsuario(usuarioLogado.getId());
        return ResponseEntity.ok(minhasOrdensConcluidas);
    }
}