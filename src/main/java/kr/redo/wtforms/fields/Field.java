package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Field<T> extends AbstractField<T> {
    Optional<T> value = Optional.empty();

    public Field(Transformer<T> transformer) {
        setTransformer(transformer);
    }

    public Optional<T> getValue() {
        return value;
    }

    @Override
    public void processData(HttpServletRequest request) {
        final String parameter = request.getParameter(getParameterName());
        if (parameter == null) {
            return;
        }
        value = getTransformer().fromParameterValue(parameter);
    }
}
