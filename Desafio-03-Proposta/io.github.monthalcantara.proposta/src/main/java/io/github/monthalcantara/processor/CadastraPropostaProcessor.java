package io.github.monthalcantara.processor;

import io.github.monthalcantara.model.Proposta;
import io.github.monthalcantara.entities.PropostaEntity;
import io.github.monthalcantara.repository.PropostaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CadastraPropostaProcessor implements Processor<Proposta> {

    private final PropostaRepository propostaRepository;

    public CadastraPropostaProcessor(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @Override
    public Proposta process(Proposta proposta) {
        final PropostaEntity propostaEntity = new PropostaEntity(proposta);

        propostaRepository.save(propostaEntity);
        Long id = propostaEntity.getId();
        log.info("Proposta salva com id {}", id);
        proposta.setId(id);

        return proposta;
    }
}
