package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.Converter;
import kr.redo.wtforms.converters.RequestParamConverter;
import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.converters.StringRequestParamConverter;
import kr.redo.wtforms.widget.SelectWidget;
import org.jetbrains.annotations.Nullable;

public class SelectField extends OptionsField<String, Converter<String>, RequestParamConverter<String>> {
    public SelectField(@Nullable String value) {
        super(value, StringConverter.STRING_CONVERTER, SelectWidget.SELECT_WIDGET, StringRequestParamConverter.STRING_REQUEST_PARAM_CONVERTER);
    }

    public SelectField() {
        this(null);
    }
}
