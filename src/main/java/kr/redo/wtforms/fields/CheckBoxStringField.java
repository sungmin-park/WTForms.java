package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class CheckBoxStringField extends CheckBoxField<String> {
    public CheckBoxStringField() {
        super(STRING_TRANSFORMER);
    }
}
