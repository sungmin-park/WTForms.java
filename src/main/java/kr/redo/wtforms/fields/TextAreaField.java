package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.widget.TextAreaWidget;

import static kr.redo.wtforms.converters.StringConverter.STRING_CONVERTER;
import static kr.redo.wtforms.widget.TextAreaWidget.TEXT_AREA_WIDGET;

public class TextAreaField extends Field<String, StringConverter, TextAreaWidget> {
    public TextAreaField() {
        super(STRING_CONVERTER, TEXT_AREA_WIDGET);
    }
}
