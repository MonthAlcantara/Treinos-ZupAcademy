package io.github.monthalcantara.casadocodigo.advice;

import lombok.Getter;
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
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ErrosApi> collect = methodArgumentNotValidException
                .getBindingResult().getFieldErrors()
                .stream()
                .map(f -> new ErrosApi(f.getDefaultMessage(), f.getField(), Objects.requireNonNull(f.getRejectedValue()).toString()))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(collect);
    }

    @Getter
    public class ErrosApi {

        private String campo;
        private String valor;
        private List<String> mensagens = new ArrayList<>();

        public ErrosApi(List<String> mensagens, String campo, String valorRejeitado) {
            this.mensagens = mensagens;
            this.campo = campo;
            this.valor = valorRejeitado;
        }

        public ErrosApi(String mensagem, String campo, String valorRejeitado) {
            this.mensagens.add(mensagem);
            this.campo = campo;
            this.valor = valorRejeitado;
        }

        private ErrosApi() {
        }
    }
}
