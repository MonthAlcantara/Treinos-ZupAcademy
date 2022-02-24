package io.github.monthalcantara.mercadolivre.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Usuario {

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

}
