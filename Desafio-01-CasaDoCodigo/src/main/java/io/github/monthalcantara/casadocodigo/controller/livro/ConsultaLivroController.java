package io.github.monthalcantara.casadocodigo.controller.livro;

import io.github.monthalcantara.casadocodigo.dto.response.LivroResponse;
import io.github.monthalcantara.casadocodigo.model.Livro;
import io.github.monthalcantara.casadocodigo.repository.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/livros")
public class ConsultaLivroController {

    private LivroRepository livroRepository;

    public ConsultaLivroController(LivroRepository livroService) {
        this.livroRepository = livroService;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<LivroResponse>> consultaLivros(){
        var livros = livroRepository.findAll();
        List<LivroResponse> livrosResponse = livros.stream().map(LivroResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(livrosResponse);

    }
}
