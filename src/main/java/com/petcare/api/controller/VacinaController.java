package com.petcare.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcare.api.model.Vacina;
import com.petcare.api.service.VacinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vacinas")
public class VacinaController {

    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @PostMapping
    public ResponseEntity<Vacina> registrar(@Valid @RequestBody Vacina vacina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaService.registrar(vacina));
    }

    @GetMapping
    public ResponseEntity<List<Vacina>> listarTodos() {
        return ResponseEntity.ok(vacinaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vacina> buscarPorId(@PathVariable Long id) {
        return vacinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<Vacina>> buscarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(vacinaService.buscarPorAnimal(animalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vacina> atualizar(@PathVariable Long id, @Valid @RequestBody Vacina vacina) {
        return ResponseEntity.ok(vacinaService.atualizar(id, vacina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        vacinaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
