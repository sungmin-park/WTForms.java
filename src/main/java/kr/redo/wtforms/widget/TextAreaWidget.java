package kr.redo.wtforms.widget;

import kr.redo.wtforms.fields.BaseField;
import org.jetbrains.annotations.NotNull;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.id;

public class TextAreaWidget extends Widget<BaseField> {
    public static final TextAreaWidget TEXT_AREA_WIDGET = new TextAreaWidget();

    @NotNull
    @Override
    public String render(BaseField field) throws Exception {
        final String parameterName = field.getParameterName();
        return new HtmlCanvas()
                .textarea(id(parameterName).name(parameterName)).content(field.getParameterValue()).toHtml();
    }
}
