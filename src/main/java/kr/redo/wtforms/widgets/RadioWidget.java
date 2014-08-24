package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.OptionsField;
import kr.redo.wtforms.fields.ParameterOption;
import kr.redo.wtforms.utils.ObjectUtils;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlAttributesFactory;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.id;

public class RadioWidget extends AbstractWidget implements OptionsWidget {
    @Override
    public String render(OptionsField<?> field) throws Exception {
        HtmlCanvas ul = new HtmlCanvas().ul(HtmlAttributesFactory.class_("wtf-widget"));
        final String parameterName = field.getParameterName();
        final String parameterValue = ObjectUtils.get(field.getParameterValue());
        ParameterOption[] parameterOptions = field.getParameterOptions();
        for (int i = 0, parameterOptionsLength = parameterOptions.length; i < parameterOptionsLength; i++) {
            ParameterOption parameterOption = parameterOptions[i];
            final String value = parameterOption.getValue();
            final String id = parameterName + "-" + i;
            final HtmlAttributes radio = id(id).name(parameterName).type("radio")
                    .value(value).checked_if(value.equals(parameterValue));
            final HtmlAttributes label = HtmlAttributesFactory.for_(id);
            ul = ul.li().input(radio).label(label).content(parameterOption.getValue())._li();
        }
        return ul._ul().toHtml();
    }

    public static final RadioWidget RADIO_WIDGET = new RadioWidget();
}
