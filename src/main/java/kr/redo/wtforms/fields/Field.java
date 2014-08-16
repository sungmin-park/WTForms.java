package kr.redo.wtforms.fields;

import kr.redo.wtforms.Form;
import kr.redo.wtforms.converters.Converter;
import kr.redo.wtforms.widget.Widget;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

public class Field<T, C extends Converter<T>> {
    @NotNull
    String name = "";

    @NotNull
    final C converter;

    @Nullable
    T value;

    @NotNull
    final Widget widget;

    @NotNull
    private Form form;

    public Field(@NotNull C converter, @NotNull Widget widget) {
        this.converter = converter;
        this.widget = widget;
    }

    public Field(@Nullable T value, C converter, Widget widget) {
        this(converter, widget);
        this.value = value;
    }

    @NotNull
    @Override
    public String toString() {
        final String parameterName = getParameterName();
        try {
            return widget.render(parameterName, parameterName, converter.toRequestParam(value));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public T getValue() {
        return value;
    }

    public void setValue(@Nullable T value) {
        this.value = value;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public Form getForm() {
        return form;
    }

    public void setForm(@NotNull Form form) {
        this.form = form;
    }

    @NotNull
    public String getParameterName() {
        return this.getForm().getWtfPrefix() + this.getName();
    }

    public void bind(@NotNull Form form, @NotNull String name) {
        this.setForm(form);
        this.setName(name);
    }

    public void processData(HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (ArrayUtils.isEmpty(parameterValues)) {
            return;
        }
        final T converted = converter.fromRequestParam(parameterValues[0]);
        if (converted != null) {
            setValue(converted);
        }
    }

    @Nullable
    public String getParameterValue() {
        return converter.toRequestParam(getValue());
    }
}
