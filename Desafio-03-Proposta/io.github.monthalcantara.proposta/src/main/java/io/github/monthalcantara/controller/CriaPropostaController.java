package io.github.monthalcantara.controller;

import io.github.monthalcantara.dto.request.NovaPropostaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/propostas")
public class CriaPropostaController {

    @PostMapping
    public ResponseEntity criaNovaProposta(@Valid @RequestBody NovaPropostaRequest novaProposta){
        return ResponseEntity.ok("ok");
    }

}
