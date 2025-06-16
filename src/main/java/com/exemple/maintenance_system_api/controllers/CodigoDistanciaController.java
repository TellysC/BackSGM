package com.exemple.maintenance_system_api.controllers;

import com.exemple.maintenance_system_api.domain.model.CodigoDistancia;
import com.exemple.maintenance_system_api.domain.model.dto.CodigoDistanciaCreateDTO;
import com.exemple.maintenance_system_api.domain.model.dto.CodigoDistanciaUpdateDTO;
import com.exemple.maintenance_system_api.services.CodigoDistanciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/codigo-distancia")
public class CodigoDistanciaController {

    private final CodigoDistanciaService codigoDistanciaService;

    @PostMapping("/criar")
    public ResponseEntity<CodigoDistancia> criar(@RequestBody CodigoDistanciaCreateDTO codigoDistanciaCreateDTO) {
        CodigoDistancia codigoDistancia = codigoDistanciaService.criar(codigoDistanciaCreateDTO);
        return ResponseEntity.status(201).body(codigoDistancia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CodigoDistancia> atualizar(@PathVariable long id,@RequestBody CodigoDistanciaUpdateDTO codigoDistanciaUpdateDTO) {
        CodigoDistancia codigoDistancia = codigoDistanciaService.atualizar(id, codigoDistanciaUpdateDTO);
        return ResponseEntity.ok(codigoDistancia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        codigoDistanciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CodigoDistancia> buscar(@PathVariable long id) {
        CodigoDistancia codigoDistancia = codigoDistanciaService.buscar(id);
        return ResponseEntity.ok(codigoDistancia);
    }

    @GetMapping()
    public ResponseEntity<List<CodigoDistancia>> listar() {
        List<CodigoDistancia> codigoDistancias = codigoDistanciaService.listar();
        return ResponseEntity.ok(codigoDistancias);
    }
}
