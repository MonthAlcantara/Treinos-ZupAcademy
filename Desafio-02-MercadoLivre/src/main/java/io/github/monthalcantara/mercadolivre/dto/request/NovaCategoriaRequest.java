package io.github.monthalcantara.mercadolivre.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.monthalcantara.mercadolivre.entity.CategoriaEntity;
import io.github.monthalcantara.mercadolivre.model.Categoria;
import io.github.monthalcantara.mercadolivre.validation.ExisteValor;
import io.github.monthalcantara.mercadolivre.validation.ValorUnico;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NovaCategoriaRequest {

    @ValorUnico(aClass = CategoriaEntity.class, field = "nome", message = "Ja existe uma categoria salva com esse nome")
    private String nome;

    @ExisteValor(field = "id", aClass = CategoriaEntity.class, message = "Não existe registro para esse id de categoria")
    private String categoriaMaeId;


    public Categoria toModel() {
        if(categoriaMaeId == null){
        return new Categoria(this.nome);

        }
        return  new Categoria(this.nome, Long.valueOf(this.categoriaMaeId));
    }
}
