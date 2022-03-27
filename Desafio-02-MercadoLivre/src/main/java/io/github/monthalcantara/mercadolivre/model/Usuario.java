package io.github.monthalcantara.mercadolivre.model;

import io.github.monthalcantara.mercadolivre.entity.UsuarioEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
public class Usuario implements UserDetails {

    private String login;

    private String senha;

    private LocalDateTime instanteCriacao;

    public Usuario(String login, SenhaLimpa senha, LocalDateTime instanteCriacao) {
        this.login = login;
        this.senha = senha.hash();
        this.instanteCriacao = instanteCriacao;
    }

    @Deprecated
    private Usuario() {
    }

    public Usuario(UsuarioEntity entity) {
        this.login = entity.getLogin();
        this.instanteCriacao = entity.getInstanteCriacao();
        this.senha = entity.getLogin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
