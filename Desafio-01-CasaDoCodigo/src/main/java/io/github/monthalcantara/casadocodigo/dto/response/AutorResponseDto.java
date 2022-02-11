package io.github.monthalcantara.casadocodigo.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.monthalcantara.casadocodigo.model.Autor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@ToString
public class AutorResponseDto {

    private Long id;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime instant;

    private AutorResponseDto() {
    }

    public AutorResponseDto(Autor autor) {
        this.nome = autor.getNome();
        this.instant = autor.getInstant();
        this.id = autor.getId();
    }

}
