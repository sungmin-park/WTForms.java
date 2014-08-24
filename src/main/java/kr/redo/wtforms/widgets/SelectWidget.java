package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.OptionsField;
import kr.redo.wtforms.fields.ParameterOption;
import kr.redo.wtforms.utils.ObjectUtils;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.value;

public class SelectWidget extends AbstractWidget implements OptionsWidget {
    @Override
    public String render(OptionsField<?> field) throws Exception {
        String parameterValue = ObjectUtils.get(field.getParameterValue());
        HtmlCanvas select = new HtmlCanvas().select(makeDefaultAttributes(field));
        for (ParameterOption parameterOption : field.getParameterOptions()) {
            final String value = parameterOption.getValue();
            final HtmlAttributes attrs = value(value).selected_if(value.equals(parameterValue));
            select = select.option(attrs).content(parameterOption.getLabel());
        }
        return select._select().toHtml();
    }

    public static final SelectWidget SELECT_WIDGET = new SelectWidget();
}
