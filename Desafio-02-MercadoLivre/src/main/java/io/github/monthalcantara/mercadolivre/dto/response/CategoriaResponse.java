package io.github.monthalcantara.mercadolivre.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.monthalcantara.mercadolivre.entity.CategoriaEntity;
import io.github.monthalcantara.mercadolivre.model.Categoria;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaResponse {

    private String nome;

    private Long categoriaMae;

    public CategoriaResponse(Categoria categoria) {
        this.nome = categoria.getNome();
        this.categoriaMae = categoria.getCategoriaMae();
    }

    public CategoriaResponse(CategoriaEntity categoria) {
        this.nome = categoria.getNome();
    }

    @Deprecated
    private CategoriaResponse() {
    }


}
