package kr.redo.wtforms.widget;

import kr.redo.wtforms.fields.BaseField;
import org.jetbrains.annotations.NotNull;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.type;

public class TextWidget extends Widget<BaseField> {
    public static final TextWidget TEXT_WIDGET = new TextWidget();

    @NotNull
    @Override
    public String render(BaseField field) throws Exception {
        final String parameterName = field.getParameterName();
        return new HtmlCanvas().input(type("text").id(parameterName).name(parameterName).value(field.getParameterValue())).toHtml();
    }
}
