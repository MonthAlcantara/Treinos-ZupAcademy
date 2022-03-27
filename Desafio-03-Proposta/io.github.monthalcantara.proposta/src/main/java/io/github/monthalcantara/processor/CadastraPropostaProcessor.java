package io.github.monthalcantara.processor;

import io.github.monthalcantar.processor.Processor;
import io.github.monthalcantara.proposta.Proposta;

public class CadastraPropostaProcessor implements Processor<Proposta> {

    @Override
    public Proposta process(Proposta proposta) {
        return proposta;
    }
}
