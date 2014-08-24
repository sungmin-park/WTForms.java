package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class OptionsField<T> extends AbstractField<T> {
    private Optional<T> value = Optional.empty();
    @SuppressWarnings("unchecked")
    private T[] options = (T[]) new Object[]{};

    public OptionsField(Transformer<T> transformer) {
        setTransformer(transformer);
    }

    @Override
    public void processData(HttpServletRequest request) {
        final String parameter = request.getParameter(getParameterName());
        if (parameter == null) {
            return;
        }
        value = Arrays.stream(options).filter(o -> getTransformer().toParameterValue(o).equals(parameter)).findFirst();
    }

    @Override
    public String render() throws Exception {
        return null;
    }

    public Optional<T> getValue() {
        return value;
    }

    public void setOptions(T[] options) {
        this.options = options;
    }
}
