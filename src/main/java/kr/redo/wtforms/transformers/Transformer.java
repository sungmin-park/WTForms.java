package kr.redo.wtforms.transformers;

import java.util.Optional;

abstract public class Transformer<T> {
    public String toParameterValue(T t) {
        return t.toString();
    }

    abstract public Optional<T> fromParameterValue(String value);
}
