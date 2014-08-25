package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.ValueTransformer;
import kr.redo.wtforms.validators.Validator;

import static kr.redo.wtforms.widgets.TextWidget.TEXT_WIDGET;

public class TextField<T> extends Field<T> {
    public TextField(ValueTransformer<T> transformer) {
        super(transformer, TEXT_WIDGET);
    }

    public TextField(ValueTransformer<T> transformer, Validator<T> validator) {
        super(transformer, TEXT_WIDGET, validator);
    }
}
