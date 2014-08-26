package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.validators.OptionsValidator;
import kr.redo.wtforms.widgets.OptionsWidget;
import kr.redo.wtforms.widgets.SelectWidget;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static kr.redo.wtforms.validators.AbstractValidator.StopValidationException;

public class OptionsField<T> extends AbstractField {
    private final List<OptionsValidator<T>> validators = new ArrayList<>();
    private final Transformer<T> transformer;
    OptionsWidget widget;
    private Optional<T> value = Optional.empty();
    @SuppressWarnings("unchecked")
    private T[] options = (T[]) new Object[]{};

    public OptionsField(Transformer<T> transformer, OptionsWidget widget) {
        this.transformer = transformer;
        this.widget = widget;
    }

    public OptionsField(Transformer<T> transformer) {
        this(transformer, SelectWidget.SELECT_WIDGET);
    }

    @SafeVarargs
    public OptionsField(Transformer<T> transformer, OptionsValidator<T>... validators) {
        this(transformer);
        Arrays.stream(validators).forEach(this.validators::add);
    }

    @Override
    public void processData(HttpServletRequest request) {
        final String parameter = request.getParameter(getParameterName());
        if (parameter == null || parameter.equals("")) {
            return;
        }
        value = Arrays.stream(options).filter(o -> transformer.toParameterValue(o).equals(parameter)).findFirst();
        if (!value.isPresent()) {
            addError("not a valid choice.");
        }
    }

    @Override
    public String render() throws Exception {
        return widget.render(this);
    }

    @Override
    public void validate() throws Exception {
        if (hasErrors()) {
            return;
        }
        for (OptionsValidator<T> validator : validators) {
            try {
                validator.validate(this);
            } catch (StopValidationException e) {
                break;
            }
        }
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
