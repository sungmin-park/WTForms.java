package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class RadioStringField extends RadioField<String> {
    public RadioStringField() {
        super(STRING_TRANSFORMER);
    }
}
