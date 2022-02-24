package io.github.monthalcantara.mercadolivre.controller;

import io.github.monthalcantara.mercadolivre.core.Processor;
import io.github.monthalcantara.mercadolivre.dto.request.NovoUsuarioRequest;
import io.github.monthalcantara.mercadolivre.dto.response.UsuarioResponse;
import io.github.monthalcantara.mercadolivre.model.Usuario;
import io.github.monthalcantara.mercadolivre.processor.CadastraUsuarioProcessorImpl;
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
@RequestMapping("/v1/usuarios")
public class CadastraUsuarioController {

    private final Processor<Usuario> usuarioProcessor;
    private Logger log = LoggerFactory.getLogger(CadastraUsuarioController.class);

    public CadastraUsuarioController(CadastraUsuarioProcessorImpl usuarioProcessor) {
        this.usuarioProcessor = usuarioProcessor;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponse> cadastraUsuario(@Valid @RequestBody NovoUsuarioRequest usuarioRequest) {
        log.info("Recebida solicitação para criaçao de novo usuario", usuarioRequest);

        final var usuario = usuarioRequest.toModel();

        log.info("Realizada conversão de request para objeto de dominio", usuario);

        usuarioProcessor.processa(usuario);

        final var usuarioResponse = new UsuarioResponse(usuario);

        log.info("Realizada conversão de dominio para objeto de resposta", usuarioResponse);

        return ResponseEntity.ok(usuarioResponse);
    }
}
