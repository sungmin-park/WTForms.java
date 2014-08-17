package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.Converter;
import kr.redo.wtforms.widget.Widget;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

public class Field<T, C extends Converter<T>> extends AbstractField<T, C> implements BaseField {

    @NotNull
    Widget<BaseField> widget;

    public Field(@NotNull C converter, @NotNull Widget<BaseField> widget) {
        super(converter);
        this.widget = widget;
    }

    public Field(@Nullable T value, C converter, @NotNull Widget<BaseField> widget) {
        this(converter, widget);
        setValue(value);
    }

    @NotNull
    @Override
    public String toString() {
        try {
            return widget.render(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void processData(HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (ArrayUtils.isEmpty(parameterValues)) {
            return;
        }
        final T converted = getConverter().fromRequestParam(parameterValues[0]);
        if (converted != null) {
            setValue(converted);
        }
    }
}
