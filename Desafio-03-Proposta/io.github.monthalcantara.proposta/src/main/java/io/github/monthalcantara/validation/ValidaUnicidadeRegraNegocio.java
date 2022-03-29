package io.github.monthalcantara.validation;

import io.github.monthalcantara.exceptions.PropostaDuplicadaException;
import io.github.monthalcantara.model.Proposta;
import io.github.monthalcantara.repository.PropostaRepository;
import io.github.monthalcantara.validations.RegraNegocioValidator;
import org.springframework.stereotype.Component;

@Component
public class ValidaUnicidadeRegraNegocio implements RegraNegocioValidator<Proposta> {
    private final PropostaRepository propostaRepository;
    private final String MESSAGEMVALIDACAO = "Já existe uma proposta com esse documento";

    public ValidaUnicidadeRegraNegocio(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @Override
    public Proposta valid(Proposta proposta) {
        final var cpfOuCnpj = proposta.getCpfOuCnpj();
        if (propostaRepository.findByCpfOuCnpj(cpfOuCnpj).isPresent())
                throw new PropostaDuplicadaException("Já existe uma proposta com esse documento");
        return proposta;
    }
}
