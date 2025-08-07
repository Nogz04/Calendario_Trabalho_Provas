package com.nogueira.calendario_trabalho_provas.service;

import com.nogueira.calendario_trabalho_provas.dto.LoginUsuarioRequestDTO;
import com.nogueira.calendario_trabalho_provas.dto.LoginUsuarioResponseDTO;
import com.nogueira.calendario_trabalho_provas.dto.UsuarioCadastroRequestDTO;
import com.nogueira.calendario_trabalho_provas.dto.UsuarioCadastroResponseDTO;
import com.nogueira.calendario_trabalho_provas.model.Usuario;
import com.nogueira.calendario_trabalho_provas.repository.UsuarioRepository;
import com.nogueira.calendario_trabalho_provas.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioAuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Cadastra um novo usuário
    public UsuarioCadastroResponseDTO cadastrar(UsuarioCadastroRequestDTO dto) {
        // Verifica se já existe e-mail ou CPF cadastrado
        boolean emailExiste = usuarioRepository.findAll()
                .stream().anyMatch(u -> u.getEmail().equals(dto.email()));
        boolean cpfExiste = usuarioRepository.findAll()
                .stream().anyMatch(u -> u.getCpf().equals(dto.cpf()));

        if (emailExiste || cpfExiste) {
            throw new RuntimeException("E-mail ou CPF já cadastrados.");
        }

        // Cria novo usuário
        Usuario usuario = new Usuario(
                null,
                dto.nome(),
                dto.email(),
                passwordEncoder.encode(dto.senha()), // senha criptografada
                dto.cpf()
        );

        Usuario salvo = usuarioRepository.save(usuario);

        return new UsuarioCadastroResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail()
        );
    }

    // Realiza login e retorna o token JWT
    public LoginUsuarioResponseDTO login(LoginUsuarioRequestDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(dto.email()))
                .findFirst();

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        Usuario usuario = usuarioOpt.get();

        // Verifica se a senha está correta
        boolean senhaCorreta = passwordEncoder.matches(dto.senha(), usuario.getSenha());

        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida.");
        }

        // Gera token JWT com e-mail do usuário
        String token = jwtService.gerarToken(usuario.getEmail());

        return new LoginUsuarioResponseDTO(
                token,
                "Bearer",
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
