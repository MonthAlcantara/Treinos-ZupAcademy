package io.github.monthalcantara.casadocodigo.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",unique = true, nullable = false)
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    private Categoria() {
    }
}
