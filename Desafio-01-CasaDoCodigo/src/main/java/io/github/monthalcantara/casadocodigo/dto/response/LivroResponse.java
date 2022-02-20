package io.github.monthalcantara.casadocodigo.dto.response;

import io.github.monthalcantara.casadocodigo.model.Livro;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
public class LivroResponse {

    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    private Integer numeroPaginas;

    private String isbn;

    private LocalDate dataPublicacao;

    private CategoriaResponse categoria;

    private AutorResponse autor;

    private LivroResponse() {
    }

    public LivroResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
        this.autor = new AutorResponse(livro.getAutor());
        this.categoria = new CategoriaResponse(livro.getCategoria());
    }
}
