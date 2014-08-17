package kr.redo.wtforms.fields;

import kr.redo.wtforms.Form;
import kr.redo.wtforms.converters.Converter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

abstract public class AbstractField<T, C extends Converter<T>> {
    @NotNull
    private C converter;
    @Nullable
    private T value;
    @NotNull
    private String name = "";
    @NotNull
    private Form form;

    public AbstractField(@NotNull C converter) {
        this.converter = converter;
    }

    @NotNull
    public C getConverter() {
        return converter;
    }

    public void setConverter(@NotNull C converter) {
        this.converter = converter;
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

    @Nullable
    public T getValue() {
        return value;
    }

    public void setValue(@Nullable T value) {
        this.value = value;
    }

    @NotNull
    public String getParameterName() {
        return this.getForm().getWtfPrefix() + this.getName();
    }

    public void bind(@NotNull Form form, @NotNull String name) {
        this.setForm(form);
        this.setName(name);
    }

    public abstract void processData(HttpServletRequest request);

    @Nullable
    public String getParameterValue() {
        return getConverter().toRequestParam(getValue());
    }
}
