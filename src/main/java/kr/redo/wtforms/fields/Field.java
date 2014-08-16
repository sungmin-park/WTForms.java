package kr.redo.wtforms.fields;

import kr.redo.wtforms.Form;
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

    public String getParameterName() {
        return this.getForm().getWtfPrefix() + this.getName();
    }

    public void bind(@NotNull Form form, @NotNull String name) {
        this.setForm(form);
        this.setName(name);
    }

    public abstract void processData(HttpServletRequest request);
}
