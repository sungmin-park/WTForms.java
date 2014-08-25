package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.widgets.MultipleValueOptionsWidget;
import org.javatuples.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static kr.redo.wtforms.widgets.CheckBoxWidget.CHECK_BOX_WIDGET;

public class MultipleValueOptionsField<T> extends AbstractField<T> {
    private List<T> values = new ArrayList<>();
    private List<T> options = new ArrayList<>();
    private MultipleValueOptionsWidget widget;

    public MultipleValueOptionsField(Transformer<T> transformer) {
        this(transformer, CHECK_BOX_WIDGET);
    }

    public MultipleValueOptionsField(Transformer<T> transformer, MultipleValueOptionsWidget widget) {
        this.setTransformer(transformer);
        this.widget = widget;
    }

    @Override
    public void processData(HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (parameterValues == null) {
            return;
        }
        final List<Pair<T, String>> optionParameters =
                options.stream().map(o -> Pair.with(o, getTransformer().toParameterValue(o))).collect(toList());
        values = Arrays.stream(parameterValues)
                .map(pv -> optionParameters.stream().filter(o -> o.getValue1().equals(pv)).findAny())
                .filter(Optional::isPresent)
                .map(Optional::get).map(Pair::getValue0)
                .collect(toList());
    }

    @Override
    public String render() throws Exception {
        return widget.render(this);
    }

    @Override
    public void validate() throws Exception {
        throw new NotImplementedException();
    }

    public List<T> getValues() {
        return values;
    }

    public List<T> getOptions() {
        return options;
    }

    public void setOptions(final List<T> options) {
        this.options = options;
    }

    public List<String> getParameterValues() {
        return values.stream().map(getTransformer()::toParameterLabel).collect(toList());
    }

    public List<ParameterOption> getParameterOptions() {
        return options.stream().map(getTransformer()::toParameterOption).collect(toList());
    }
}
