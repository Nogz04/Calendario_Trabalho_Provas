package com.nogueira.calendario_trabalho_provas.controller;

import com.nogueira.calendario_trabalho_provas.model.Usuario;
import com.nogueira.calendario_trabalho_provas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET /usuarios - busca todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // GET /usuarios/{id} - busca usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /usuarios/{id} - atualiza usuário pelo ID
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody @Valid Usuario novoUsuario) {
        return usuarioService.atualizar(id, novoUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /usuarios/{id} - deleta usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = usuarioService.remover(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // 204 No Content - deletado com sucesso
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found - usuário não encontrado
        }
    }
}
