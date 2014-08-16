package kr.redo.wtforms.widget;

import org.jetbrains.annotations.NotNull;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.type;

public class TextWidget extends Widget {
    @NotNull
    @Override
    public String render(@NotNull String id, @NotNull String name, String value) throws Exception {
        return new HtmlCanvas().input(type("text").id(id).name(name).value(value)).toHtml();
    }

    public static final TextWidget TEXT_WIDGET = new TextWidget();
}
