package io.github.monthalcantara.casadocodigo.controller.estado;

import io.github.monthalcantara.casadocodigo.dto.request.NovoEstadoDto;
import io.github.monthalcantara.casadocodigo.dto.response.EstadoResponse;
import io.github.monthalcantara.casadocodigo.model.Estado;
import io.github.monthalcantara.casadocodigo.processor.CadastraEstadoProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/estados")
public class CadastraEstadoController {

    private final CadastraEstadoProcessor estadoProcessor;

    public CadastraEstadoController(CadastraEstadoProcessor estadoProcessor) {
        this.estadoProcessor = estadoProcessor;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoResponse> cadastraEstado(@Valid @RequestBody NovoEstadoDto estadoDto) {

        Estado estado = estadoProcessor.processa(estadoDto.getNome(), estadoDto.getPaisId());

        return ResponseEntity.ok(new EstadoResponse(estado));
    }
}
