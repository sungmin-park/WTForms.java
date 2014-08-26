package kr.redo.wtforms.fields;

import kr.redo.wtforms.validators.MultipleValueOptionsValidator;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class CheckBoxStringField extends CheckBoxField<String> {
    public CheckBoxStringField() {
        super(STRING_TRANSFORMER);
    }

    @SafeVarargs
    public CheckBoxStringField(MultipleValueOptionsValidator<String>... validators) {
        super(STRING_TRANSFORMER, validators);
    }
}
