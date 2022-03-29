package io.github.monthalcantara.interceptor;

import io.github.monthalcantara.exceptions.PropostaDuplicadaException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdviceHandle {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<String> collect = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(collect);
    }
    @ExceptionHandler(PropostaDuplicadaException.class)
    public ResponseEntity propostaDuplicadaException(PropostaDuplicadaException ex) {
        return ResponseEntity.status(ex.getSTATUS()).body(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity methodArgumentNotValidException(Exception ex) {
        return ResponseEntity.badRequest().body("Deu ruim. Contate a equipe responsável");
    }

}
