package io.github.monthalcantara.casadocodigo.dto.request;

import io.github.monthalcantara.casadocodigo.model.Estado;
import io.github.monthalcantara.casadocodigo.model.Pais;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class NovoEstadoDto {
    @NotBlank
    private String nome;

    @NotNull
    private Long paisId;

    public Estado toModel(Pais pais) {
        return new Estado(nome, pais);
    }
}
