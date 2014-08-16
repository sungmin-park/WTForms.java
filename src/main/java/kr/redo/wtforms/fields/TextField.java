package kr.redo.wtforms.fields;

import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.rendersnake.HtmlAttributesFactory.type;

public class TextField extends Field<String> {
    @NotNull
    @Override
    public String toString() {
        try {
            final String parameterName = getParameterName();
            final HtmlAttributes attributes = type("text").id(parameterName).name(parameterName).value(getValue());
            final HtmlCanvas canvas = new HtmlCanvas();
            return canvas.input(attributes).toHtml();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processData(@NotNull HttpServletRequest request) {
        final String[] parameterValues = request.getParameterValues(getParameterName());
        if (ArrayUtils.isEmpty(parameterValues)) {
            return;
        }
        setValue(parameterValues[0]);
    }
}
