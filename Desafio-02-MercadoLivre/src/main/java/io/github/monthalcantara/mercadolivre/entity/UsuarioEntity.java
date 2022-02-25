package io.github.monthalcantara.mercadolivre.entity;

import io.github.monthalcantara.mercadolivre.model.Usuario;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "usuarios", indexes = @Index(unique = true, name = "login", columnList = "login"))
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    private LocalDateTime instanteCriacao;

    public UsuarioEntity(String login, String senha, LocalDateTime instanteCriacao) {
        this.login = login;
        this.senha = senha;
        this.instanteCriacao = instanteCriacao;
    }

    @Deprecated
    private UsuarioEntity() {
    }

    public UsuarioEntity(Usuario usuario) {
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.instanteCriacao = usuario.getInstanteCriacao();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
