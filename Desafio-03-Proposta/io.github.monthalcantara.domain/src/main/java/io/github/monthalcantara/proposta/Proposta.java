package io.github.monthalcantara.proposta;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Proposta {
    private String cpfOuCnpj;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    private Proposta() {
    }

    public Proposta(String cpfOuCnpj, String email, String nome, String endereco, BigDecimal salario) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
}
