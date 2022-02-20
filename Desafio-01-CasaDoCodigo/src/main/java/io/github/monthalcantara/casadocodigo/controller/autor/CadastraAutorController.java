package io.github.monthalcantara.casadocodigo.controller.autor;

import io.github.monthalcantara.casadocodigo.dto.request.NovoAutorDto;
import io.github.monthalcantara.casadocodigo.dto.response.AutorResponse;
import io.github.monthalcantara.casadocodigo.repository.AutorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/autores")
public class CadastraAutorController {

    private final AutorRepository autorRepository;

    public CadastraAutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    private final Logger log = LoggerFactory.getLogger(CadastraAutorController.class);

    @PostMapping
    @Transactional
    public ResponseEntity<AutorResponse> cadastraAutor(@Valid @RequestBody NovoAutorDto novoAutorDto) {
        log.info("Recebida request para cadastro de novo autor: {}", novoAutorDto);

        final var autor = novoAutorDto.toModel();

        log.info("Realizada conversão para o modelo de autor: {}", autor);

        autorRepository.save(autor);

        log.info("Autor cadastrado no banco com id {}", autor.getId());

        final var autorResponseDto = new AutorResponse(autor);

        return ResponseEntity.ok(autorResponseDto);
    }
}
