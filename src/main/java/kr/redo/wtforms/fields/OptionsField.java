package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.Converter;
import kr.redo.wtforms.widget.Widget;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;

public class OptionsField<T, C extends Converter<T>> extends AbstractField<T, C> implements BaseOptionsField {
    @SuppressWarnings("unchecked")
    @NotNull
    private T[] options = (T[]) new Class[0];

    @NotNull
    private Widget<BaseOptionsField> widget;

    public OptionsField(@NotNull C converter, @NotNull Widget<BaseOptionsField> widget) {
        super(converter);
        this.widget = widget;
    }

    public OptionsField(T value, @NotNull C converter, @NotNull Widget<BaseOptionsField> widget) {
        this(converter, widget);
        this.setValue(value);
    }

    @Override
    public ParameterOptionPair[] getParameterOptionValues() {
        return new ParameterOptionPair[0];
    }

    @NotNull
    @Override
    public String toString() {
        try {
            return this.widget.render(this);
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
            if (ArrayUtils.contains(getOptions(), converted)) {
                setValue(converted);
            }
        }
    }

    @NotNull
    public T[] getOptions() {
        return options;
    }

    public void setOptions(@NotNull T[] options) {
        this.options = options;
    }
}
