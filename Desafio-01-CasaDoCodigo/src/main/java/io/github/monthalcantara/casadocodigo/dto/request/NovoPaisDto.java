package io.github.monthalcantara.casadocodigo.dto.request;

import io.github.monthalcantara.casadocodigo.model.Pais;
import io.github.monthalcantara.casadocodigo.validation.ValorUnico;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class NovoPaisDto {
    @NotBlank
    @ValorUnico(aClass = Pais.class, field = "nome", message = "Ja existe um Pais com este nome cadastrado")
    private String nome;

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
