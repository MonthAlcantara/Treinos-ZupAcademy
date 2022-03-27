package io.github.monthalcantara.mercadolivre.controller.categoria;

import io.github.monthalcantara.mercadolivre.dto.response.CategoriaResponse;
import io.github.monthalcantara.mercadolivre.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("v1/categorias")
@RestController
public class BuscaCategoriaController {

    private final Logger log = LoggerFactory.getLogger(BuscaCategoriaController.class);
    private final CategoriaRepository categoriaRepository;

    public BuscaCategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/{id}")
    public List<CategoriaResponse> buscaPorCategoriaMae(@PathVariable("id") Long id) {
        return categoriaRepository.findAllByCategoriaMaeId(id)
                .stream()
                .map(CategoriaResponse::new)
                .collect(Collectors.toList()
                );
    }

}
