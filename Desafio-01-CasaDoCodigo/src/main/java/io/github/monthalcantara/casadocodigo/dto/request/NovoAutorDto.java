package io.github.monthalcantara.casadocodigo.dto.request;

import io.github.monthalcantara.casadocodigo.model.Autor;
import io.github.monthalcantara.casadocodigo.validation.ValorUnico;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@ToString
public class NovoAutorDto {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    @ValorUnico(aClass = Autor.class, field = "email", message = "Já existe um autor com esse email")
    private String email;

    @NotBlank
    @Size(max = 400)
    private String descricao;

    private LocalDateTime instant;

    private NovoAutorDto() {
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
