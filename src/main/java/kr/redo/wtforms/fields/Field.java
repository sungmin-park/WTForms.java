package kr.redo.wtforms.fields;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Field extends AbstractField {
    Optional<String> value;

    public Optional<String> getValue() {
        return value;
    }

    @Override
    public void processData(HttpServletRequest request) {
        value = Optional.ofNullable(request.getParameter(getParameterName()));
    }
}
