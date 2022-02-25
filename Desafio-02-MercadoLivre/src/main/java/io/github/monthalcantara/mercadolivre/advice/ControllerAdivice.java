package io.github.monthalcantara.mercadolivre.advice;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdivice {

    private final Logger log = LoggerFactory.getLogger(ControllerAdivice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity capturaMethodArgumentNotValidException(MethodArgumentNotValidException argumentNotValidException) {
        List<String> errosList = argumentNotValidException
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).
                collect(Collectors.toList());
        log.error("Falha na validação", argumentNotValidException);
        return new ResponseEntity(new ErroApi(errosList), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity capturaException(Exception exception) {
//        log.info("Erro não mapeado", exception.getMessage());
//        return new ResponseEntity(new ErroApi(List.of("Erro interno")), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Getter
    private class ErroApi {
        private List<String> mensagens;

        public ErroApi(List<String> mensagens) {
            this.mensagens = mensagens;
        }
    }
}
