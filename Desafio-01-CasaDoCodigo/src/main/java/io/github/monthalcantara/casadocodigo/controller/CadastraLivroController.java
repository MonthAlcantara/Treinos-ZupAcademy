package io.github.monthalcantara.casadocodigo.controller;

import io.github.monthalcantara.casadocodigo.dto.request.NovoLivroDto;
import io.github.monthalcantara.casadocodigo.dto.response.LivroResponse;
import io.github.monthalcantara.casadocodigo.model.Livro;
import io.github.monthalcantara.casadocodigo.processor.CadastraLivroProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/livros")
public class CadastraLivroController {

    private CadastraLivroProcessor livroService;

    public CadastraLivroController(CadastraLivroProcessor livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponse> cadastraLivro(@Valid @RequestBody NovoLivroDto novoLivro){

        Livro livro = novoLivro.toModel();

        Livro livroSalvo = livroService.processa(livro, novoLivro.getAutor(), novoLivro.getCategoria());

        return ResponseEntity.ok(new LivroResponse(livroSalvo));

    }
}
