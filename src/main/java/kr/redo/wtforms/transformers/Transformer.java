package kr.redo.wtforms.transformers;

import kr.redo.wtforms.fields.ParameterOption;

import java.util.Optional;

abstract public class Transformer<T> {
    public String toParameterValue(T t) {
        return t.toString();
    }
    public String toParameterLabel(T t) {
        return toParameterValue(t);
    }

    public ParameterOption toParameterOption(T t) {
        return new ParameterOption(toParameterValue(t), toParameterValue(t));
    }
}
