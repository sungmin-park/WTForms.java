package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.validators.Validator;
import kr.redo.wtforms.widgets.TextWidget;
import kr.redo.wtforms.widgets.Widget;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static kr.redo.wtforms.validators.AbstractValidator.StopValidationException;

public class Field<T> extends AbstractField<T> {
    final private List<Validator<T>> validators = new ArrayList<>();
    private Optional<T> value = Optional.empty();
    private Widget widget;

    public Field(Transformer<T> transformer, Widget widget, Validator<T> validator) {
        this(transformer, widget);
        validators.add(validator);
    }

    public Field(Transformer<T> transformer, Widget widget) {
        this.widget = widget;
        setTransformer(transformer);
    }

    public Field(Transformer<T> transformer) {
        this(transformer, TextWidget.TEXT_WIDGET);
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
        return value.map(v -> getTransformer().toParameterValue(v));
    }

    public void setValue(Optional<T> value) {
        this.value = value;
    }
}
