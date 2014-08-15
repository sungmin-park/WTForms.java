package kr.redo.wtforms.fields;

import org.jetbrains.annotations.NotNull;
import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;

import java.io.IOException;

import static org.rendersnake.HtmlAttributesFactory.type;

public class TextField extends Field {
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
}
