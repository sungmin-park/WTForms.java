package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.validators.MultipleValueOptionsValidator;

public class CheckBoxField<T> extends MultipleValueOptionsField<T> {
    public CheckBoxField(Transformer<T> transformer) {
        super(transformer);
    }


    @SafeVarargs
    public CheckBoxField(Transformer<T> transformer, MultipleValueOptionsValidator<T>... validators) {
        super(transformer, validators);
    }
}
