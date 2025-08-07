package com.nogueira.calendario_trabalho_provas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> professores;

    @Column(unique = true)
    private String nome;

    private String descricao;

    @ElementCollection
    private List<String> trabalhos;

    @ElementCollection
    private List<String> provas;






}
