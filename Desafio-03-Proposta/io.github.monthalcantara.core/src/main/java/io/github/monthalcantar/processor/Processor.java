package io.github.monthalcantar.processor;

public interface Processor<T> {

    T process(T t);
}
