package kr.redo.wtforms.fields;

import kr.redo.wtforms.Form;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

abstract public class Field<T> {
    @NotNull
    String name;
    @Nullable

    T value;
    @NotNull
    private Form form;

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
        processData(parameterValues[0]);
    }

    protected abstract void processData(@NotNull String parameterValue);
}
