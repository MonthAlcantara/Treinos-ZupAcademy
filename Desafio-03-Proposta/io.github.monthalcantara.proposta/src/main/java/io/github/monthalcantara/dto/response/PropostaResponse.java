package io.github.monthalcantara.dto.response;

import io.github.monthalcantara.model.Proposta;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
public class PropostaResponse extends RepresentationModel<PropostaResponse> {
    private String cpfOuCnpj;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    public PropostaResponse(Proposta proposta) {
        this.cpfOuCnpj = proposta.getCpfOuCnpj();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
    }
}
