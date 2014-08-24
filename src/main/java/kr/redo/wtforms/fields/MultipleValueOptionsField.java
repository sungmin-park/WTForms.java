package kr.redo.wtforms.fields;

import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class MultipleValueOptionsField extends AbstractField {
    private String[] values = {};
    private String[] options = {};

    @Override
    public void processData(HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (parameterValues == null) {
            return;
        }
        values = Arrays.stream(parameterValues)
                .filter(parameterValue -> ArrayUtils.contains(options, parameterValue))
                .toArray(String[]::new);
    }

    @Override
    public String render() throws Exception {
        return null;
    }

    public String[] getValues() {
        return values;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
