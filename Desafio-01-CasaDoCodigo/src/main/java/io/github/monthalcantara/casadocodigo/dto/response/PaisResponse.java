package io.github.monthalcantara.casadocodigo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.monthalcantara.casadocodigo.model.Pais;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaisResponse {

    private Long id;

    private String nome;

    private PaisResponse() {
    }

    public PaisResponse(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
    }
}
