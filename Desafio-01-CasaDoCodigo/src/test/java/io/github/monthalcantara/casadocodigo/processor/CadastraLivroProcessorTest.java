package io.github.monthalcantara.casadocodigo.processor;

import io.github.monthalcantara.casadocodigo.exception.RecursoNaoEncontradoException;
import io.github.monthalcantara.casadocodigo.model.Autor;
import io.github.monthalcantara.casadocodigo.model.Categoria;
import io.github.monthalcantara.casadocodigo.model.Livro;
import io.github.monthalcantara.casadocodigo.repository.AutorRepository;
import io.github.monthalcantara.casadocodigo.repository.CategoriaRepository;
import io.github.monthalcantara.casadocodigo.repository.LivroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CadastraLivroProcessorTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    @Mock
    private LivroRepository livroRepository;
    @Mock
    private AutorRepository autorRepository;

    private CadastraLivroProcessor cadastraLivroProcessor;

    @BeforeEach
    void init() {
        cadastraLivroProcessor = new CadastraLivroProcessor(categoriaRepository, livroRepository, autorRepository);
    }

    @Test
    @DisplayName("Existe categoria, existe autor, salva livro")
    void cadastraLivroProcessorSucesso() {
        final var livro = getLivroMock();
        final var optionalCategoria = getCategoriaMock();
        final var autorOptional = Optional.of(new Autor("Autor", "autor@autor.com.br", "descricao", LocalDateTime.now()));

        Mockito.when(categoriaRepository.findById(1L)).thenReturn(optionalCategoria);
        Mockito.when(autorRepository.findById(1L)).thenReturn(autorOptional);
        Mockito.when(livroRepository.save(livro)).thenReturn(livro);

        final var livroProcessado = cadastraLivroProcessor.processa(livro, 1L, 1L);

        Assertions.assertNotNull(livroProcessado);
    }

    @Test
    @DisplayName("Existe categoria, não existe autor, lança RecursoNaoEncontradoException")
    void cadastraLivroProcessorErro() {
        final var livro = getLivroMock();
        final var optionalCategoria = getCategoriaMock();

        Mockito.when(categoriaRepository.findById(1L)).thenReturn(optionalCategoria);
        Mockito.when(autorRepository.findById(1L)).thenReturn(Optional.empty());

        final var recursoNaoEncontradoException = Assertions.assertThrows(RecursoNaoEncontradoException.class, () -> cadastraLivroProcessor.processa(livro, 1L, 1L));

        Assertions.assertTrue(recursoNaoEncontradoException.getMensagem().contains("Não foi encontrado autor com o Id informado"));
    }

    @Test
    @DisplayName("Não existe categoria e lança RecursoNaoEncontradoException")
    void cadastraLivroProcessorErroDois() {
        final var livro = getLivroMock();
        final var optionalCategoria = getCategoriaMock();

        Mockito.when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        final var recursoNaoEncontradoException = Assertions.assertThrows(RecursoNaoEncontradoException.class, () -> cadastraLivroProcessor.processa(livro, 1L, 1L));

        Assertions.assertTrue(recursoNaoEncontradoException.getMensagem().contains("Não foi encontrado categoria com o Id informado"));
    }

    private Optional<Categoria> getCategoriaMock() {
        return Optional.of(new Categoria("Tecnologia"));
    }

    private Livro getLivroMock() {
        return new Livro(1L,
                "titulo",
                "resumo",
                "sumario",
                BigDecimal.valueOf(50L),
                50,
                "ISBN",
                LocalDate.now());
    }
}