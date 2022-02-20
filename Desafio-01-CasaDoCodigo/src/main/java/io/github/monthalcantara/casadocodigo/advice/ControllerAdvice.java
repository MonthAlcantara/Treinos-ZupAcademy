package io.github.monthalcantara.casadocodigo.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.monthalcantara.casadocodigo.exception.RecursoNaoEncontradoException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrosApi>> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ErrosApi> collect = methodArgumentNotValidException
                .getBindingResult().getFieldErrors()
                .stream()
                .map(f -> new ErrosApi(f.getDefaultMessage(), f.getField(), Objects.requireNonNull(f.getRejectedValue()).toString()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(collect);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> methodArgumentNotValidException(Exception exception) {

        return ResponseEntity.internalServerError().body("Erro interno. Por favor contate a equipe responsável");
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrosApi> methodArgumentNotValidException(RecursoNaoEncontradoException exception) {
        ErrosApi errosApi = new ErrosApi(List.of(exception.getMensagem()), exception.getCampo());
        return new ResponseEntity<>(errosApi, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrosApi {

        private String campo;
        private String valor;
        private List<String> mensagens = new ArrayList<>();

        public ErrosApi(List<String> mensagens, String campo) {
            this.mensagens = mensagens;
            this.campo = campo;
        }

        public ErrosApi(String mensagem, String campo, String valorRejeitado) {
            this.mensagens.add(mensagem);
            this.campo = campo;
            this.valor = valorRejeitado;
        }

        public ErrosApi(List<String> mensagens) {
            this.mensagens = mensagens;
        }

        private ErrosApi() {
        }
    }
}
