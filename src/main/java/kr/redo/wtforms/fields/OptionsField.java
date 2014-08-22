package kr.redo.wtforms.fields;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class OptionsField extends AbstractField {
    private Optional<String> value = Optional.empty();
    private String[] options = {};

    @Override
    public void processData(HttpServletRequest request) {
        final String parameter = request.getParameter(getParameterName());
        if (parameter == null) {
            return;
        }
        for (String option : options) {
            if (option.equals(parameter)) {
                value = Optional.of(parameter);
            }
        }
    }

    public Optional<String> getValue() {
        return value;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
