package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.validators.OptionsValidator;

public class SelectField<T> extends OptionsField<T> {

    public SelectField(Transformer<T> transformer) {
        super(transformer);
    }

    @SafeVarargs
    public SelectField(Transformer<T> transformer, OptionsValidator<T>... validators) {
        super(transformer, validators);
    }
}
