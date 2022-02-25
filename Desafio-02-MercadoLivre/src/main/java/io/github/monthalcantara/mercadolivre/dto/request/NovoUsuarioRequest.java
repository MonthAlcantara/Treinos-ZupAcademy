package io.github.monthalcantara.mercadolivre.dto.request;

import io.github.monthalcantara.mercadolivre.entity.UsuarioEntity;
import io.github.monthalcantara.mercadolivre.model.SenhaLimpa;
import io.github.monthalcantara.mercadolivre.model.Usuario;
import io.github.monthalcantara.mercadolivre.validation.ValorUnico;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class NovoUsuarioRequest {

    @NotBlank
    @ValorUnico(aClass = UsuarioEntity.class, field = "login", message = "Ja existe um registro com esse login registrado")
    @Email
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    private LocalDateTime instanteCriacao;


    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.instanteCriacao = LocalDateTime.now();
    }

    @Deprecated
    private NovoUsuarioRequest() {
    }

    public Usuario toModel() {
        this.instanteCriacao = LocalDateTime.now();
        SenhaLimpa senhaLimpa = new SenhaLimpa(this.senha);
        return new Usuario(this.login, senhaLimpa, this.instanteCriacao);
    }
}
