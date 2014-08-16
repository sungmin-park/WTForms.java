package kr.redo.wtforms.widget;

import org.jetbrains.annotations.NotNull;
import org.rendersnake.HtmlCanvas;

import java.io.IOException;

import static org.rendersnake.HtmlAttributesFactory.type;

public class TextWidget extends Widget {
    @NotNull
    @Override
    public String render(@NotNull String id, @NotNull String name, String value) {
        try {
            return new HtmlCanvas().input(type("text").id(id).name(name).value(value)).toHtml();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
