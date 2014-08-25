package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.MultipleValueOptionsField;
import kr.redo.wtforms.fields.ParameterOption;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlAttributesFactory;
import org.rendersnake.HtmlCanvas;

import java.util.List;

import static org.rendersnake.HtmlAttributesFactory.id;

public class CheckBoxWidget extends AbstractWidget implements MultipleValueOptionsWidget {
    @Override
    public String render(MultipleValueOptionsField<?> field) throws Exception {
        HtmlCanvas ul = new HtmlCanvas().ul(HtmlAttributesFactory.class_("wtf-widget"));
        final String parameterName = field.getParameterName();
        final List<String> parameterValues = field.getParameterValues();
        List<ParameterOption> parameterOptions = field.getParameterOptions();
        for (int i = 0, parameterOptionsLength = parameterOptions.size(); i < parameterOptionsLength; i++) {
            ParameterOption parameterOption = parameterOptions.get(i);
            final String value = parameterOption.getValue();
            final String id = parameterName + "-" + i;
            final HtmlAttributes checkbox = id(id).name(parameterName).type("checkbox")
                    .value(value).checked_if(parameterValues.contains(value));
            final HtmlAttributes label = HtmlAttributesFactory.for_(id);
            ul = ul.li().input(checkbox).label(label).content(parameterOption.getValue())._li();
        }
        return ul._ul().toHtml();
    }

    public static final CheckBoxWidget CHECK_BOX_WIDGET = new CheckBoxWidget();
}
