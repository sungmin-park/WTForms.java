package kr.redo.wtforms.transformers;

import java.util.Optional;

abstract public class ValueTransformer<T> extends Transformer<T>{
    abstract public Optional<T> fromParameterValue(String value);
}
