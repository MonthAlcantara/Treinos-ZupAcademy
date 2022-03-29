package io.github.monthalcantara.processor;

public interface Processor<T> {

    T process(T t);
}
