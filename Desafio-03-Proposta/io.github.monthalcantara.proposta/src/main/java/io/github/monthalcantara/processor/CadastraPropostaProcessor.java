package io.github.monthalcantara.processor;

import io.github.monthalcantara.entities.PropostaEntity;
import io.github.monthalcantara.model.Proposta;
import io.github.monthalcantara.repository.PropostaRepository;
import io.github.monthalcantara.validations.RegraNegocioValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CadastraPropostaProcessor implements Processor<Proposta> {

    private final PropostaRepository propostaRepository;
    private final RegraNegocioValidator regraNegocioValidator;

    public CadastraPropostaProcessor(PropostaRepository propostaRepository, RegraNegocioValidator regraNegocioValidator) {
        this.propostaRepository = propostaRepository;
        this.regraNegocioValidator = regraNegocioValidator;
    }

    @Override
    public Proposta process(Proposta proposta) {
        final PropostaEntity propostaEntity = new PropostaEntity(proposta);
        regraNegocioValidator.valid(proposta);
        propostaRepository.save(propostaEntity);
        Long id = propostaEntity.getId();
        log.info("Proposta salva com id {}", id);
        proposta.setId(id);

        return proposta;
    }
}
