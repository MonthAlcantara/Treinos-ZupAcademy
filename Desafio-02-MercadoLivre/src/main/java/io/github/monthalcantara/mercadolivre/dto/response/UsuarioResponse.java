package io.github.monthalcantara.mercadolivre.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.monthalcantara.mercadolivre.model.Usuario;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UsuarioResponse {

    private String login;

    private String senha;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime instanteCriacao;


    public UsuarioResponse(Usuario usuario) {
       login = usuario.getLogin();
       senha = usuario.getSenha();
       instanteCriacao = usuario.getInstanteCriacao();
    }
}
