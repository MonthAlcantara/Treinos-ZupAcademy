package io.github.monthalcantara.mercadolivre.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class SenhaLimpa {

    @NotBlank
    @Length(min = 6)
    private String senha;

    @Deprecated
    private SenhaLimpa() {
    }

    public SenhaLimpa(String senha) {
        this.senha = senha;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
