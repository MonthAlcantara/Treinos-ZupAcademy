package io.github.monthalcantara.casadocodigo.dto.response;

import io.github.monthalcantara.casadocodigo.model.Categoria;
import lombok.Getter;

@Getter
public class CategoriaResponse {

    private Long id;

    private String nome;

    public CategoriaResponse(Categoria categoria) {
        this.id= categoria.getId();
        this.nome = categoria.getNome();
    }

    private CategoriaResponse() {
    }
}
