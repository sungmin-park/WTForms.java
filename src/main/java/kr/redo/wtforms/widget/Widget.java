package kr.redo.wtforms.widget;

import kr.redo.wtforms.fields.BaseField;
import org.jetbrains.annotations.NotNull;

abstract public class Widget<T extends BaseField> {
    @NotNull
    abstract public String render(T field) throws Exception;
}
