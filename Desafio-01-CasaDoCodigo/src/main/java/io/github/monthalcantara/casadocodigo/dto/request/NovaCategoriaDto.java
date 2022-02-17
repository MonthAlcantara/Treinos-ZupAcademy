package io.github.monthalcantara.casadocodigo.dto.request;

import io.github.monthalcantara.casadocodigo.model.Categoria;
import io.github.monthalcantara.casadocodigo.validation.ValorUnico;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class NovaCategoriaDto {

    @NotBlank
    @ValorUnico(aClass = Categoria.class, field = "nome", message = "O nome deve ser informado")
    private String nome;

    public NovaCategoriaDto(String nome) {
        this.nome = nome;
    }

    private NovaCategoriaDto() {
    }

    public Categoria toModel(){
        return new Categoria(this.nome);
    }
}
