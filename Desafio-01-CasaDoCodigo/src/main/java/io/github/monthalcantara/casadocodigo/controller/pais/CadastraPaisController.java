package io.github.monthalcantara.casadocodigo.controller.pais;

import io.github.monthalcantara.casadocodigo.dto.request.NovoPaisDto;
import io.github.monthalcantara.casadocodigo.dto.response.PaisResponse;
import io.github.monthalcantara.casadocodigo.model.Pais;
import io.github.monthalcantara.casadocodigo.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/paises")
public class CadastraPaisController {

    private final PaisRepository paisRepository;

    public CadastraPaisController(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PaisResponse> cadastraPais(@Valid @RequestBody NovoPaisDto paisRequest){
        Pais pais = paisRequest.toModel();
        paisRepository.save(pais);
        return ResponseEntity.ok(new PaisResponse(pais));
    }
}
