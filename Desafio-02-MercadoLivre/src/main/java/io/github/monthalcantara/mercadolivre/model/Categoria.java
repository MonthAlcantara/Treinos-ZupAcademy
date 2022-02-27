package io.github.monthalcantara.mercadolivre.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Categoria {

    private String nome;

    private Long categoriaMae;

    public Categoria(String nome, Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }
    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated
    private Categoria() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(nome, categoria.nome) && Objects.equals(categoriaMae, categoria.categoriaMae);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, categoriaMae);
    }
}
