package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class Field {
    String name;
    protected String[] values = {};
    private Form form;

    public String getName() {
        return name;
    }

    public void processData(HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (parameterValues == null || parameterValues.length == 0) {
            return;
        }
        values = Arrays.copyOf(parameterValues, parameterValues.length);
    }

    public String getParameterName() {
        return form.prefix() + name;
    }

    public void bind(Form form, String name) {
        this.form = form;
        this.name = name;
    }
}
