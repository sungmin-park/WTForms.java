package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.widgets.TextWidget;
import kr.redo.wtforms.widgets.Widget;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Field<T> extends AbstractField<T> {
    private Optional<T> value = Optional.empty();
    private Widget widget;

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

    public Optional<String> getParameterValue() {
        return value.map(v -> getTransformer().toParameterValue(v));
    }
}
