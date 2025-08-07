package com.nogueira.calendario_trabalho_provas.security;

import com.nogueira.calendario_trabalho_provas.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class DetalhesUsuario implements UserDetails {

    private final Usuario usuario;

    public DetalhesUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return usuario.getId();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Sem perfis por enquanto
    }

    @Override
    public boolean isAccountNonExpired() {
        // Retorna true para indicar que a conta do usuário não está expirada
        // Poderá ser usado para desativar contas após um certo tempo
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Retorna true para indicar que a conta não está bloqueada
        // Pode ser usado para bloquear usuários manualmente ou após muitas tentativas erradas de login
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Retorna true para indicar que as credenciais (como senha) ainda são válidas
        // Pode ser útil para forçar troca de senha após determinado período
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Retorna true para indicar que a conta está ativa e habilitada para login
        // Pode ser usado para ativação de conta via e-mail ou desativação por admin
        return true;
    }

}
