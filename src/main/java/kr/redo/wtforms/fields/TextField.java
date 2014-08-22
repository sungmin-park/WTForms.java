package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;

public class TextField extends Field<String> {
    public TextField() {
        super(STRING_TRANSFORMER);
    }
}
