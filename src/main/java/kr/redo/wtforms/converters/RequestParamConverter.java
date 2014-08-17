package kr.redo.wtforms.converters;

import org.jetbrains.annotations.Nullable;

public class RequestParamConverter<R> {
    @Nullable
    public String toRequestParam(@Nullable R value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
