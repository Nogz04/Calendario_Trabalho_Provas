package com.nogueira.calendario_trabalho_provas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioRequestDTO(

        @NotBlank(message = "Por favor, informe o seu e-mail.")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "Por favor, informe a sua senha.")
        String senha

)
{}
