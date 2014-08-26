package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.widgets.OptionsWidget;
import kr.redo.wtforms.widgets.SelectWidget;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class OptionsField<T> extends AbstractField {
    private Optional<T> value = Optional.empty();
    @SuppressWarnings("unchecked")
    private T[] options = (T[]) new Object[]{};

    private final Transformer<T> transformer;
    OptionsWidget widget;

    public OptionsField(Transformer<T> transformer, OptionsWidget widget) {
        this.transformer = transformer;
        this.widget = widget;
    }

    public OptionsField(Transformer<T> transformer) {
        this(transformer, SelectWidget.SELECT_WIDGET);
    }

    @Override
    public void processData(HttpServletRequest request) {
        final String parameter = request.getParameter(getParameterName());
        if (parameter == null) {
            return;
        }
        value = Arrays.stream(options).filter(o -> transformer.toParameterValue(o).equals(parameter)).findFirst();
    }

    @Override
    public String render() throws Exception {
        return widget.render(this);
    }

    @Override
    public void validate() throws Exception {
        throw new NotImplementedException();
    }

    public Optional<T> getValue() {
        return value;
    }

    public void setOptions(T[] options) {
        this.options = options;
    }

    public Optional<String> getParameterValue() {
        return value.map(transformer::toParameterValue);
    }

    public ParameterOption[] getParameterOptions() {
        return Arrays.stream(options)
                .map(o -> new ParameterOption(transformer.toParameterValue(o), transformer.toParameterLabel(o)))
                .toArray(ParameterOption[]::new);
    }
}
