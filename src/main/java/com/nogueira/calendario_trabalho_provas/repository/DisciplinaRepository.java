package com.nogueira.calendario_trabalho_provas.repository;

import com.nogueira.calendario_trabalho_provas.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina,Long> {
}
