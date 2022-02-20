package io.github.monthalcantara.casadocodigo.controller.livro;

import io.github.monthalcantara.casadocodigo.dto.response.LivroResponse;
import io.github.monthalcantara.casadocodigo.repository.LivroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/livros")
public class ConsultaLivroController {

    private final LivroRepository livroRepository;

    public ConsultaLivroController(LivroRepository livroService) {
        this.livroRepository = livroService;
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<LivroResponse>> consultaLivros() {
        var livros = livroRepository.findAll();
        List<LivroResponse> livrosResponse = livros.stream().map(LivroResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(livrosResponse);

    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<LivroResponse> consultaLivroPeloId(@PathVariable Long id) {
        var livros = livroRepository.findById(id);
        var livrosResponse = livros.map(LivroResponse::new).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(livrosResponse);

    }
}
