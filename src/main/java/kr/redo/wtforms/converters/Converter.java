package kr.redo.wtforms.converters;

import org.jetbrains.annotations.Nullable;

public abstract class Converter<R> {
    @Nullable
    public abstract R fromRequestParam(@Nullable String param);

    @Nullable
    public String toRequestParam(@Nullable R value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
