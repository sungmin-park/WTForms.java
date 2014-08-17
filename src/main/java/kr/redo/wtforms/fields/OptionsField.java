package kr.redo.wtforms.fields;

import kr.redo.wtforms.converters.Converter;
import kr.redo.wtforms.converters.RequestParamConverter;
import kr.redo.wtforms.widget.Widget;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class OptionsField<T, C extends Converter<T>, R extends RequestParamConverter<T>> extends AbstractField<T, C> implements BaseOptionsField {
    @SuppressWarnings("unchecked")
    @NotNull
    private T[] options = (T[]) new Class[0];

    @NotNull
    private R optionTextConveterr;

    @NotNull
    private Widget<BaseOptionsField> widget;

    public OptionsField(@NotNull C converter, @NotNull Widget<BaseOptionsField> widget, @NotNull R optionTextConveterr) {
        super(converter);
        this.widget = widget;
        this.optionTextConveterr = optionTextConveterr;
    }

    public OptionsField(T value, @NotNull C converter, @NotNull Widget<BaseOptionsField> widget, @NotNull R optionTextConveterr) {
        this(converter, widget, optionTextConveterr);
        this.setValue(value);
    }

    @Override
    public ParameterOptionPair[] getParameterOptionValues() {
        return Arrays.stream(getOptions()).map(o -> {
            final String value = getConverter().toRequestParam(o);
            final String text = optionTextConveterr.toRequestParam(o);
            if (text == null || value == null) {
                return null;
            }
            return new ParameterOptionPair(value, text);
        }).filter(e -> e != null).toArray(ParameterOptionPair[]::new);
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
        for (T option : options) {
            if (parameterValues[0].equals(getConverter().toRequestParam(option))) {
                setValue(option);
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
