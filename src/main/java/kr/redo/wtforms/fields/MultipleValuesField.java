package kr.redo.wtforms.fields;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class MultipleValuesField extends AbstractField {
    private String[] values = {};

    @Override
    public void processData(HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (parameterValues == null) {
            return;
        }
        values = Arrays.copyOf(parameterValues, parameterValues.length);
    }

    @Override
    public String render() throws Exception {
        return null;
    }

    @Override
    public void validate() throws Exception {
        throw new NotImplementedException();
    }

    public String[] getValues() {
        return values;
    }
}
