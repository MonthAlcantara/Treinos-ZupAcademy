package io.github.monthalcantara.casadocodigo.exception;

import lombok.Getter;

@Getter
public class RecursoNaoEncontradoException extends RuntimeException{

    private final String mensagem;
    private final String status = "404";
    private final String campo;

    public RecursoNaoEncontradoException(String mensagem, String campo) {
        super();
        this.mensagem = mensagem;
        this.campo = campo;
    }
}
