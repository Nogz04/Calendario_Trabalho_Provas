package com.nogueira.calendario_trabalho_provas.repository;

import com.nogueira.calendario_trabalho_provas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
