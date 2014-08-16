package kr.redo.wtforms.widget;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.rendersnake.HtmlCanvas;

import static org.rendersnake.HtmlAttributesFactory.id;

public class TextareaWidget extends Widget {
    @NotNull
    @Override
    public String render(@NotNull String id, @NotNull String name, @Nullable String value) throws Exception {
        return new HtmlCanvas().textarea(id(id).name(name)).content(value).toHtml();
    }
}
