package kr.redo.wtforms.widget;

import kr.redo.wtforms.fields.BaseOptionsField;
import kr.redo.wtforms.fields.ParameterOptionPair;
import org.jetbrains.annotations.NotNull;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.id;
import static org.rendersnake.HtmlAttributesFactory.value;

public class SelectWidget extends Widget<BaseOptionsField> {
    @NotNull
    @Override
    public String render(BaseOptionsField field) throws Exception {
        final String parameterName = field.getParameterName();
        final String parameterValue = field.getParameterValue();
        HtmlCanvas select = new HtmlCanvas().select(id(parameterName).name(parameterName));
        for (ParameterOptionPair parameterOptionPair : field.getParameterOptionValues()) {
            final String value = parameterOptionPair.getValue();
            HtmlAttributes attrs = value(value);
            if (value.equals(parameterValue)) {
                attrs = attrs.selected("");
            }
            select = select.option(attrs).content(parameterOptionPair.getText());
        }
        return select.toHtml();
    }

    public static final SelectWidget SELECT_WIDGET = new SelectWidget();
}
