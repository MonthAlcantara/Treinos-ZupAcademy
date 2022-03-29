package io.github.monthalcantara.entities;

import io.github.monthalcantara.model.Proposta;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "propostas")
public class PropostaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String cpfOuCnpj;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private BigDecimal salario;

    public PropostaEntity() {
    }

    public PropostaEntity(Proposta proposta) {
        this.cpfOuCnpj = proposta.getCpfOuCnpj();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
    }
}
