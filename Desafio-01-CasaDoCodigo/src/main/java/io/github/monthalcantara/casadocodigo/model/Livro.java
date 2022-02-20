package io.github.monthalcantara.casadocodigo.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroPaginas;
    private String isbn;
    private LocalDate dataPublicacao;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Autor autor;

    public Livro(Long id, String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroPaginas, String isbn, LocalDate dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
    }

    public Livro adicionaAutorCategoria(Autor autor, Categoria categoria) {
        this.autor = autor;
        this.categoria = categoria;
        return this;
    }

    public static LivroBuilder Builder() {
        return new LivroBuilder();
    }

    public static class LivroBuilder {
        private Long id;
        private String titulo;
        private String resumo;
        private String sumario;
        private BigDecimal preco;
        private Integer numeroPaginas;
        private String isbn;
        private LocalDate dataPublicacao;
        private Categoria categoria;
        private Autor autor;

        private LivroBuilder() {
        }

        public LivroBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LivroBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public LivroBuilder resumo(String resumo) {
            this.resumo = resumo;
            return this;
        }

        public LivroBuilder sumario(String sumario) {
            this.sumario = sumario;
            return this;
        }

        public LivroBuilder preco(BigDecimal preco) {
            this.preco = preco;
            return this;
        }

        public LivroBuilder numeroPaginas(Integer numeroPaginas) {
            this.numeroPaginas = numeroPaginas;
            return this;
        }

        public LivroBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public LivroBuilder dataPublicacao(LocalDate dataPublicacao) {
            this.dataPublicacao = dataPublicacao;
            return this;
        }

        public Livro build() {
            return new Livro(this.id,
                    this.titulo,
                    this.resumo,
                    this.sumario,
                    this.preco,
                    this.numeroPaginas,
                    this.isbn,
                    this.dataPublicacao);
        }
    }
}
