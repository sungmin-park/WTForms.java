package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.AbstractField;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlAttributesFactory;

abstract public class AbstractWidget {
    HtmlAttributes makeDefaultAttributes(AbstractField field) {
        return HtmlAttributesFactory.id(field.getParameterName()).name(field.getParameterName());
    }
}
