package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.IntegerTransformer;

public class IntegerField extends Field<Integer> {
    public IntegerField() {
        super(IntegerTransformer.INTEGER_TRANSFORMER);
    }
}
