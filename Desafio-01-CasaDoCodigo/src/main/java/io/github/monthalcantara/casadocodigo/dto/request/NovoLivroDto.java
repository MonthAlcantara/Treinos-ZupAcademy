package io.github.monthalcantara.casadocodigo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.monthalcantara.casadocodigo.model.Livro;
import io.github.monthalcantara.casadocodigo.validation.ValorUnico;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
public class NovoLivroDto {

    @NotBlank
    @ValorUnico(aClass = Livro.class, field = "titulo", message = "O titulo do livro deve ser informado")
    private String titulo;

    @NotBlank
    @Size(min = 100)
    private String resumo;

    private String sumario;

    @Min(20)
    private BigDecimal preco;

    @Min(100)
    private Integer numeroPaginas;

    @ISBN
    @ValorUnico(aClass = Livro.class, field = "isbn")
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataPublicacao;

    @NotNull
    private Long categoria;

    @NotNull
    private Long autor;

    public Livro toModel(){
        return Livro.Builder()
                .dataPublicacao(this.dataPublicacao)
                .titulo(this.titulo)
                .isbn(this.isbn)
                .numeroPaginas(this.numeroPaginas)
                .resumo(this.resumo)
                .sumario(this.sumario)
                .preco(this.preco)
                .build();
    }


    private NovoLivroDto() {
    }
}
