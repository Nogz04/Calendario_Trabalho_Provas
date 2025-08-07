package com.nogueira.calendario_trabalho_provas.service;

import com.nogueira.calendario_trabalho_provas.model.Disciplina;
import com.nogueira.calendario_trabalho_provas.repository.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Lista todas as disciplinas
    public List<Disciplina> buscarTodas() {
        return disciplinaRepository.findAll();
    }

    // Busca disciplina por ID, lança exceção se não encontrar
    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada"));
    }

    // Atualiza uma disciplina existente
    public Optional<Disciplina> atualizar(Long id, Disciplina disciplinaDadosAtualizados) {
        return disciplinaRepository.findById(id).map(disciplina -> {
            disciplina.setNome(disciplinaDadosAtualizados.getNome());
            disciplina.setDescricao(disciplinaDadosAtualizados.getDescricao());
            disciplina.setProvas(disciplinaDadosAtualizados.getProvas());
            disciplina.setTrabalhos(disciplinaDadosAtualizados.getTrabalhos());
            disciplina.setProfessores(disciplinaDadosAtualizados.getProfessores());
            return disciplinaRepository.save(disciplina);
        });
    }

    // Remove uma disciplina pelo ID
    public boolean remover(Long id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Novo metodo para salvar uma nova disciplina
    public Disciplina cadastrar(Disciplina novaDisciplina) {
        return disciplinaRepository.save(novaDisciplina);
    }
}
