package kr.redo.wtforms.converters;

import org.jetbrains.annotations.Nullable;

public abstract class Converter<R> extends RequestParamConverter<R> {
    @Nullable
    public abstract R fromRequestParam(@Nullable String param);
}
