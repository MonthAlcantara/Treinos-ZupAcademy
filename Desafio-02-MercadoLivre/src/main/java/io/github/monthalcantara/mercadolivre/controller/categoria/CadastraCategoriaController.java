package io.github.monthalcantara.mercadolivre.controller.categoria;

import io.github.monthalcantara.mercadolivre.dto.request.NovaCategoriaRequest;
import io.github.monthalcantara.mercadolivre.dto.response.CategoriaResponse;
import io.github.monthalcantara.mercadolivre.processor.CadastraCategoriaProcessorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RequestMapping("v1/categorias")
@RestController
public class CadastraCategoriaController {

    private final Logger log = LoggerFactory.getLogger(CadastraCategoriaController.class);
    private CadastraCategoriaProcessorImpl cadastraCategoriaProcessor;

    public CadastraCategoriaController(CadastraCategoriaProcessorImpl cadastraCategoriaProcessor) {
        this.cadastraCategoriaProcessor = cadastraCategoriaProcessor;
    }

    @Transactional
    @PostMapping
    public ResponseEntity cadastraCategoria(@Valid @RequestBody NovaCategoriaRequest categoriaRequest) {

        log.info("Recebida request para criacao de nova categoria");

        var toModel = categoriaRequest.toModel();

        log.info("Request convertida para model de categoria");

        var processa = cadastraCategoriaProcessor.processa(toModel);

        return ResponseEntity.ok(new CategoriaResponse(processa));
    }


}
