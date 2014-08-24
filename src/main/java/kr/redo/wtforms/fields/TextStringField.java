package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class TextStringField extends Field<String> {
    public TextStringField() {
        super(STRING_TRANSFORMER);
    }
}
