package io.github.monthalcantara.mercadolivre.core;

public interface Processor<T> {

    public T processa(T t);
}
