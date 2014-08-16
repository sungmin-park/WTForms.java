package kr.redo.wtforms.fields;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;

public class IntegerField extends Field<Integer> {
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
