package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;

public class CheckBoxField<T> extends MultipleValueOptionsField<T>{
    public CheckBoxField(Transformer<T> transformer) {
        super(transformer);
    }
}
