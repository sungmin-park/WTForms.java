package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class SelectStringField extends OptionsField<String> {
    public SelectStringField() {
        super(STRING_TRANSFORMER);
    }
}
