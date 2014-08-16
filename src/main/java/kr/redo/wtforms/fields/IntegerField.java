package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.IntegerConverter;
import kr.redo.wtforms.widget.TextWidget;
import org.jetbrains.annotations.Nullable;

import static kr.redo.wtforms.converters.IntegerConverter.INTEGER_CONVERTER;

public class IntegerField extends Field<Integer, IntegerConverter, TextWidget> {
    public IntegerField() {
        this(null);
    }

    public IntegerField(@Nullable Integer defaultValue) {
        super(defaultValue, INTEGER_CONVERTER, TextWidget.TEXT_WIDGET);
    }
}
