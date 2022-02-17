package io.github.monthalcantara.casadocodigo.controller;

import io.github.monthalcantara.casadocodigo.dto.request.NovaCategoriaDto;
import io.github.monthalcantara.casadocodigo.dto.response.CategoriaResponse;
import io.github.monthalcantara.casadocodigo.model.Categoria;
import io.github.monthalcantara.casadocodigo.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/categorias")
public class CadastraCategoriaController {

    private CategoriaRepository repository;
    private final Logger log = LoggerFactory.getLogger(CadastraCategoriaController.class);

    public CadastraCategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> cadastraCategoria(@Valid @RequestBody NovaCategoriaDto categoriaDto) {

        log.info("recebida requisição para cadastro de nova categoria: {}", categoriaDto);

        Categoria categoria = categoriaDto.toModel();

        log.info("Realizada conversão para nova categoria");

        repository.save(categoria);

        log.info("Nova categoria cadastrada com sucesso");

        return ResponseEntity.ok(new CategoriaResponse(categoria));
    }

}
