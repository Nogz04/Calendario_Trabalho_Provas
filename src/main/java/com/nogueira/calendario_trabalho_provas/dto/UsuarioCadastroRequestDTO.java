package com.nogueira.calendario_trabalho_provas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroRequestDTO(


        @NotBlank(message = "Nome do usuário é obrigatório")
        String nome,

        @NotBlank(message = "E-mail do usuário é obrigatório")
        @Email(message = "Formato do e-mail inválido.")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 15)
        String senha,

        @NotBlank(message = "O CPF é obrigatório")
        @Size(max = 11)
        String cpf

    )
{}
