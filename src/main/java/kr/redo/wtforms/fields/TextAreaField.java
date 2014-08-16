package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.StringConverter;

import static kr.redo.wtforms.converters.StringConverter.STRING_CONVERTER;
import static kr.redo.wtforms.widget.TextAreaWidget.TEXT_AREA_WIDGET;

public class TextAreaField extends Field<String, StringConverter> {
    public TextAreaField() {
        super(STRING_CONVERTER, TEXT_AREA_WIDGET);
    }
}
