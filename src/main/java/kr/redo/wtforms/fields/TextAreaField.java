package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.widget.TextareaWidget;

public class TextAreaField extends Field<String, StringConverter, TextareaWidget>{
    public TextAreaField() {
        super(new StringConverter(), new TextareaWidget());
    }
}
