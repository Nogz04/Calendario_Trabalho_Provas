package com.nogueira.calendario_trabalho_provas.controller;

import com.nogueira.calendario_trabalho_provas.model.Disciplina;
import com.nogueira.calendario_trabalho_provas.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    // GET /disciplinas
    @GetMapping
    public ResponseEntity<List<Disciplina>> buscarTodas() {
        List<Disciplina> disciplinas = disciplinaService.buscarTodas();
        return ResponseEntity.ok(disciplinas);
    }

    // GET /disciplinas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        try {
            Disciplina disciplina = disciplinaService.buscarPorId(id);
            return ResponseEntity.ok(disciplina);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /disciplinas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id,
                                                @RequestBody @Valid Disciplina novaDisciplina) {
        return disciplinaService.atualizar(id, novaDisciplina)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /disciplinas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = disciplinaService.remover(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /disciplinas - cadastrar nova disciplina
    @PostMapping
    public ResponseEntity<Disciplina> cadastrar(@RequestBody @Valid Disciplina novaDisciplina) {
        Disciplina disciplinaSalva = disciplinaService.cadastrar(novaDisciplina);
        return ResponseEntity.status(201).body(disciplinaSalva);
    }
}
