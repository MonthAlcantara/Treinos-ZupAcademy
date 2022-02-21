package io.github.monthalcantara.casadocodigo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.monthalcantara.casadocodigo.model.Estado;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EstadoResponse {
    private String nome;

    private Long paisId;

    public EstadoResponse(Estado estado) {
        this.nome = estado.getNome();
        if (estado.temPaisCadastrado()) {
            this.paisId = estado.buscaPaisCadastrado();
        }
    }

    private EstadoResponse(){}
}
