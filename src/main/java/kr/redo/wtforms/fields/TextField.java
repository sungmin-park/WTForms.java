package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;

import static kr.redo.wtforms.widgets.TextWidget.TEXT_WIDGET;

public class TextField<T> extends Field<T> {
    public TextField(Transformer<T> transformer) {
        super(transformer, TEXT_WIDGET);
    }
}
