package kr.redo.wtforms.fields;

import org.jetbrains.annotations.NotNull;

public class ParameterOptionPair {
    @NotNull
    private String value;
    @NotNull
    private String text;

    public ParameterOptionPair(@NotNull String value, @NotNull String text) {
        this.value = value;
        this.text = text;
    }

    @NotNull
    public String getValue() {
        return value;
    }

    @NotNull
    public String getText() {
        return text;
    }
}
