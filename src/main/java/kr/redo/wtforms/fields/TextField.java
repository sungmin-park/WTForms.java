package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.widget.TextWidget;
import org.jetbrains.annotations.Nullable;

public class TextField extends Field<String, StringConverter, TextWidget> {
    public TextField() {
        this(null);
    }

    public TextField(@Nullable String value) {
        super(value, new StringConverter(), new TextWidget());
    }
}
