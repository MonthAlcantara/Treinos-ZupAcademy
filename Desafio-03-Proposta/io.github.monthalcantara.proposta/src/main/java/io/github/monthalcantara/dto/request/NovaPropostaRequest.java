package io.github.monthalcantara.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.monthalcantara.validations.CpfOuCnpj;
import io.github.monthalcantara.model.Proposta;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NovaPropostaRequest {

    @CpfOuCnpj
    @NotBlank
    private String cpfOuCnpj;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;

    public Proposta toDomain() {
        return Proposta.builder()
                .cpfOuCnpj(this.cpfOuCnpj)
                .email(this.email)
                .nome(this.nome)
                .endereco(this.endereco)
                .salario(this.salario)
                .build();
    }
}
