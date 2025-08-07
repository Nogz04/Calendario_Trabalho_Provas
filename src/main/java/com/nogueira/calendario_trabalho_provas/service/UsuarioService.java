package com.nogueira.calendario_trabalho_provas.service;

import com.nogueira.calendario_trabalho_provas.model.Usuario;
import com.nogueira.calendario_trabalho_provas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
    }

    // Busca o usuario com o ID passado por parametro e seta os dados dele com base nos valores de usuarioDadosAtualizados passados por parametro via Front ou Postman
    public Optional<Usuario> atualizar(Long id, Usuario usuarioDadosAtualizados) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioDadosAtualizados.getNome());
            usuario.setEmail(usuarioDadosAtualizados.getEmail());
            usuario.setSenha(usuarioDadosAtualizados.getSenha());
            usuario.setCpf(usuarioDadosAtualizados.getCpf());
            return usuarioRepository.save(usuario);
        });
    }

    // Remove um usuário pelo ID.
    // Retorna boolean indicando sucesso ou falha.
    public boolean remover(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true; // removido com sucesso
        }
        return false; // usuário não encontrado
    }
}
