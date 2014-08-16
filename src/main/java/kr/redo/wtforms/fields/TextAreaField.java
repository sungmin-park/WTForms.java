package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.widget.TextAreaWidgets;

import static kr.redo.wtforms.converters.StringConverter.STRING_CONVERTER;
import static kr.redo.wtforms.widget.TextAreaWidgets.TEXT_AREA_WIDGET;

public class TextAreaField extends Field<String, StringConverter, TextAreaWidgets> {
    public TextAreaField() {
        super(STRING_CONVERTER, TEXT_AREA_WIDGET);
    }
}
