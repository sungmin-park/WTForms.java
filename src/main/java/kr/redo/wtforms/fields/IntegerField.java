package kr.redo.wtforms.fields;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.servlet.http.HttpServletRequest;

public class IntegerField extends Field{
    @Nullable
    private Integer value;

    @Nullable
    public Integer getValue() {
        return value;
    }

    public void setValue(@Nullable Integer value) {
        this.value = value;
    }

    public void processData(@NotNull HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (ArrayUtils.isEmpty(parameterValues)) {
            return;
        }
        try {
            setValue(Integer.parseInt(parameterValues[0]));
        } catch (NumberFormatException ignored) {
        }
    }
}
