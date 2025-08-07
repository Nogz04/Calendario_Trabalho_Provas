package com.nogueira.calendario_trabalho_provas.controller;

import com.nogueira.calendario_trabalho_provas.dto.LoginUsuarioRequestDTO;
import com.nogueira.calendario_trabalho_provas.dto.LoginUsuarioResponseDTO;
import com.nogueira.calendario_trabalho_provas.dto.UsuarioCadastroRequestDTO;
import com.nogueira.calendario_trabalho_provas.dto.UsuarioCadastroResponseDTO;
import com.nogueira.calendario_trabalho_provas.service.UsuarioAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioAuthController {

    @Autowired
    private UsuarioAuthService authService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioCadastroResponseDTO> cadastrar(@RequestBody @Valid UsuarioCadastroRequestDTO dto) {
        return ResponseEntity.ok(authService.cadastrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUsuarioResponseDTO> login(@RequestBody @Valid LoginUsuarioRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
