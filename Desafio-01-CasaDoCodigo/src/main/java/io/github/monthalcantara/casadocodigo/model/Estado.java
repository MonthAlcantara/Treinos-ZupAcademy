package io.github.monthalcantara.casadocodigo.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@Getter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

    public Boolean temPaisCadastrado(){
        return Objects.nonNull(this.pais);
    }

    public Long buscaPaisCadastrado(){
        return Optional.of(pais)
                .map(Pais::getId)
                .orElse(null);
    }

    private Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }
}
