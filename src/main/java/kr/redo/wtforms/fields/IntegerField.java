package kr.redo.wtforms.fields;

import org.jetbrains.annotations.NotNull;

public class IntegerField extends Field<Integer> {
    @Override
    protected void processData(@NotNull String parameterValue) {
        try {
            setValue(Integer.parseInt(parameterValue));
        } catch (NumberFormatException ignored) {
        }
    }
}
