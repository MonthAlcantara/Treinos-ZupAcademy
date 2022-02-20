package io.github.monthalcantara.casadocodigo.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "descricao", nullable = false, length = 400)
    private String descricao;
    @Column(name = "instant", nullable = false)
    private LocalDateTime instant;

    private Autor() {
    }

    public Autor(String nome, String email, String descricao, LocalDateTime instant) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.instant = instant;
    }

    public static AutorBuilder createAutor() {
        return new AutorBuilder();
    }

    public static class AutorBuilder {
        private String nome;
        private String email;
        private String descricao;
        private LocalDateTime instant;

        private AutorBuilder() {
        }

        public AutorBuilder nome(String nome) {
            this.nome = nome;
            Assert.hasText(nome, "O campo nome deve ser preenchido");
            return this;
        }

        public AutorBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AutorBuilder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public AutorBuilder instant(LocalDateTime instant) {
            this.instant = instant;
            return this;
        }

        public Autor builder() {
            return new Autor(this.nome, this.email, this.descricao, this.instant);
        }
    }

}
