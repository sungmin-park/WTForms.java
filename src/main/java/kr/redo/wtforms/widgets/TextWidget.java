package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.Field;
import kr.redo.wtforms.utils.ObjectUtils;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.type;

public class TextWidget implements Widget {
    public static final TextWidget TEXT_WIDGET = new TextWidget();

    @Override
    public String render(Field<?> field) throws Exception {
        final HtmlAttributes attributes = type("text")
                .id(field.getParameterName()).name(field.getParameterName())
                .value(ObjectUtils.get(field.getParameterValue()));
        return new HtmlCanvas().input(attributes).toHtml();
    }
}
