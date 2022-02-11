package io.github.monthalcantara.casadocodigo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.monthalcantara.casadocodigo.model.Autor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@ToString
public class AutorDto {

    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 400)
    private String descricao;
    private LocalDateTime instant;

    private AutorDto() {
    }

    public Autor toModel() {
    return Autor.createAutor()
            .nome(this.nome)
            .email(this.email)
            .descricao(this.descricao)
            .instant(LocalDateTime.now())
            .builder();
    }
}
