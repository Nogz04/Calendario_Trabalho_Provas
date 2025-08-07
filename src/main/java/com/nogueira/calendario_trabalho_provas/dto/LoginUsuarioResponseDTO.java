package com.nogueira.calendario_trabalho_provas.dto;

/**
 *
 * @param token token que carrega os dados do usuario criptografado
 * @param tipo tipo de token que vamos usar
 * @param id  ID do usuario que iremos retornar no LoginUsuarioResponseDTO
 * @param nome NOME do usuario que iremos retornar no LoginUsuarioResponseDTO
 * @param email EMAIL do usuario que iremos retornar no LoginUsuarioResponseDTO
 */

// Preciso terminar de comentar.

public record LoginUsuarioResponseDTO (

        String token,
        String tipo,

        Long id,
        String nome,
        String email

        // O LoginUsuarioResponseDTO irá retornar o token
        // para salvar as credenciais de login do usuário
        // em formato token para a cada requisição não precisar
        // soliciar o login.

        // Irá retornar apenas o token e tipo de token para
        // proteger os dados sensíveis do usuário como: SENHA, CPF...

)
{}
