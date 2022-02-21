package io.github.monthalcantara.casadocodigo.processor;

import io.github.monthalcantara.casadocodigo.exception.RecursoNaoEncontradoException;
import io.github.monthalcantara.casadocodigo.model.Estado;
import io.github.monthalcantara.casadocodigo.model.Pais;
import io.github.monthalcantara.casadocodigo.repository.EstadoRepository;
import io.github.monthalcantara.casadocodigo.repository.PaisRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastraEstadoProcessor {

    private final EstadoRepository estadoRepository;
    private final PaisRepository paisRepository;

    public CadastraEstadoProcessor(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @Transactional
    public Estado processa(String nome, Long paisId) {

        var paisEncontrado = paisRepository.findById(paisId)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoException("Não foi encontrado pais com esse id: " + paisId, "pais")
                );

        if (existeEstadoParaOPais(nome, paisEncontrado)) {
            throw new IllegalArgumentException("Ja existe um estado com esse nome cadastrado para o Pais");
        }

        var estado = new Estado(nome, paisEncontrado);

        return estadoRepository.save(estado);
    }

    private Boolean existeEstadoParaOPais(String nome, Pais paisEncontrado) {
        return estadoRepository.findByNomeAndPaisId(nome, paisEncontrado.getId()).isPresent();
    }
}
