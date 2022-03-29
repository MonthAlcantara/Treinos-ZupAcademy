package io.github.monthalcantara.controller;

import io.github.monthalcantara.dto.request.NovaPropostaRequest;
import io.github.monthalcantara.dto.response.PropostaResponse;
import io.github.monthalcantara.model.Proposta;
import io.github.monthalcantara.processor.CadastraPropostaProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/propostas")
public class CriaPropostaController {

    private final CadastraPropostaProcessor cadastraPropostaProcessor;

    public CriaPropostaController(CadastraPropostaProcessor cadastraPropostaProcessor) {
        this.cadastraPropostaProcessor = cadastraPropostaProcessor;
    }

    @PostMapping
    public ResponseEntity criaNovaProposta(@Valid @RequestBody NovaPropostaRequest novaProposta) {
        var proposta = novaProposta.toDomain();
        proposta = cadastraPropostaProcessor.process(proposta);
        return new ResponseEntity(geraLink(proposta), HttpStatus.CREATED);
    }

    private PropostaResponse geraLink(Proposta proposta) {
        final var propostaResponse = new PropostaResponse(proposta);
        final var link = WebMvcLinkBuilder.linkTo(CriaPropostaController.class).slash(proposta.getId()).withSelfRel();
        propostaResponse.add(link);
        return propostaResponse;
    }
}
