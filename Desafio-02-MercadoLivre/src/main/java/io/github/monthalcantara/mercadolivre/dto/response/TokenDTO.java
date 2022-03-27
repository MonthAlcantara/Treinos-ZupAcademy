package io.github.monthalcantara.mercadolivre.dto.response;

/*
 * Classe criada para enviar o token e o tipo do token de forma mais organizada, poderia
 * ter retornado o token puro também. (Apenas questão de organização aqui)
 */
public class TokenDTO {

    private String token;
    private String tipo;

    public TokenDTO(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getToken() {
        return token;
    }
}

