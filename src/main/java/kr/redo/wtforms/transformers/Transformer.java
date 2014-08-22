package kr.redo.wtforms.transformers;

import java.util.Optional;

abstract public class Transformer<T> {

    abstract public Optional<T> fromParameterValue(String value);
}
