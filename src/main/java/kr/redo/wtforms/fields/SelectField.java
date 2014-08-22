package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class SelectField extends OptionsField<String> {
    public SelectField() {
        super(STRING_TRANSFORMER);
    }
}
