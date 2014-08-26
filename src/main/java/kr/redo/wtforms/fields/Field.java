package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.ValueTransformer;
import kr.redo.wtforms.validators.Validator;
import kr.redo.wtforms.widgets.TextWidget;
import kr.redo.wtforms.widgets.Widget;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static kr.redo.wtforms.validators.AbstractValidator.StopValidationException;

public class Field<T> extends AbstractField {
    final private List<Validator<T>> validators = new ArrayList<>();
    private Optional<T> value = Optional.empty();
    private Widget widget;
    private ValueTransformer<T> transformer;

    public Field(ValueTransformer<T> transformer, Widget widget, Validator<T> validator) {
        this(transformer, widget);
        validators.add(validator);
    }

    public Field(ValueTransformer<T> transformer, Widget widget) {
        this.transformer = transformer;
        this.widget = widget;
    }

    public Field(ValueTransformer<T> transformer) {
        this(transformer, TextWidget.TEXT_WIDGET);
    }

    public Optional<T> getValue() {
        return value;
    }

    public void setValue(Optional<T> value) {
        this.value = value;
    }

    @Override
    public void processData(HttpServletRequest request) {
        final String parameter = request.getParameter(getParameterName());
        if (parameter == null) {
            return;
        }
        value = transformer.fromParameterValue(parameter);
    }

    @Override
    public String render() throws Exception {
        return widget.render(this);
    }

    @Override
    public void validate() throws Exception {
        for (Validator<T> validator : validators) {
            try {
                validator.validate(this);
            } catch (StopValidationException e) {
                return;
            }
        }
    }

    public Optional<String> getParameterValue() {
        return value.map((Function<T, String>) transformer::toParameterValue);
    }
}
