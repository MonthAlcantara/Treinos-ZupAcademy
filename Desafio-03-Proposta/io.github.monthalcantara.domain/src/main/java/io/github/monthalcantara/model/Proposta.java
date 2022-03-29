package io.github.monthalcantara.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Builder
public class Proposta {
    @Getter
    @Setter
    private Long id;
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

    public Proposta(Long id, String cpfOuCnpj, String email, String nome, String endereco, BigDecimal salario) {
        this.id = id;
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }
}
