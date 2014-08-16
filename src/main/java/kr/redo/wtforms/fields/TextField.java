package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.widget.TextWidget;
import org.jetbrains.annotations.Nullable;

import static kr.redo.wtforms.converters.StringConverter.STRING_CONVERTER;
import static kr.redo.wtforms.widget.TextWidget.TEXT_WIDGET;

public class TextField extends Field<String, StringConverter, TextWidget> {
    public TextField() {
        this(null);
    }

    public TextField(@Nullable String value) {
        super(value, STRING_CONVERTER, TEXT_WIDGET);
    }
}
