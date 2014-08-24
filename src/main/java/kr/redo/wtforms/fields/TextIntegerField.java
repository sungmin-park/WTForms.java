package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.IntegerTransformer.INTEGER_TRANSFORMER;

public class TextIntegerField extends TextField<Integer> {
    public TextIntegerField() {
        super(INTEGER_TRANSFORMER);
    }
}
