package io.github.monthalcantara.exceptions;

import lombok.Getter;

@Getter
public abstract class RegraDeNegocioException extends RuntimeException {

    private final Integer STATUS = 422;
    private final String MESSAGE;

    RegraDeNegocioException(String MESSAGE) {
        super(MESSAGE);
        this.MESSAGE = MESSAGE;
    }

}
