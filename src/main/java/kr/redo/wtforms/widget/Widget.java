package kr.redo.wtforms.widget;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract public class Widget {
    @NotNull
    abstract public String render(@NotNull String id, @NotNull String name, @Nullable String value);
}
