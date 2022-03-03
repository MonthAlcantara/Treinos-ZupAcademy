package io.github.monthalcantara.mercadolivre.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "categorias", indexes = @Index(name = "nome", columnList = "nome", unique = true))
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    private CategoriaEntity categoriaMae;

    public CategoriaEntity(String nome, CategoriaEntity categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public CategoriaEntity(String nome) {
        this.nome = nome;
    }

    private CategoriaEntity() {
    }

}
