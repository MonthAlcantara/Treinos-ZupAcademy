package io.github.monthalcantara.casadocodigo.processor;

import io.github.monthalcantara.casadocodigo.exception.RecursoNaoEncontradoException;
import io.github.monthalcantara.casadocodigo.model.Livro;
import io.github.monthalcantara.casadocodigo.repository.AutorRepository;
import io.github.monthalcantara.casadocodigo.repository.CategoriaRepository;
import io.github.monthalcantara.casadocodigo.repository.LivroRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

import static java.lang.String.format;

@Service
public class CadastraLivroProcessor {

    private CategoriaRepository categoriaRepository;
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public CadastraLivroProcessor(CategoriaRepository repository, LivroRepository livroRepository, AutorRepository autorRepository) {
        this.categoriaRepository = repository;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public Livro processa(Livro livro, Long autor, Long categoria){
        var categoriaEncontrada = categoriaRepository.findById(categoria).orElseThrow(lanceRecursoNaoEncontrado("categoria"));
        var autorEncontrado = autorRepository.findById(autor).orElseThrow(lanceRecursoNaoEncontrado("autor"));
        livro.adicionaAutorCategoria(autorEncontrado, categoriaEncontrada);
        return livroRepository.save(livro);
    }

    private Supplier<RecursoNaoEncontradoException> lanceRecursoNaoEncontrado(String campo) {
        return () -> new RecursoNaoEncontradoException("Não foi encontrado "+ campo +" com o Id informado", campo);
    }
}
