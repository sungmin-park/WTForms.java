package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.StringTransformer;
import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.validators.Validator;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class TextStringField extends TextField<String> {
    public TextStringField(Validator<String> validator) {
        super(STRING_TRANSFORMER, validator);
    }

    public TextStringField() {
        super(STRING_TRANSFORMER);
    }
}
