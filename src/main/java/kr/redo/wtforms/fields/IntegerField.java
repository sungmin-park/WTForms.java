package kr.redo.wtforms.fields;

import static kr.redo.wtforms.transformers.IntegerTransformer.INTEGER_TRANSFORMER;

public class IntegerField extends Field<Integer> {
    public IntegerField() {
        super(INTEGER_TRANSFORMER);
    }
}
