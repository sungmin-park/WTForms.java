package kr.redo.wtforms.converters;

import org.jetbrains.annotations.Nullable;

public class StringConverter extends Converter<String> {
    @Nullable
    @Override
    public String fromRequestParam(@Nullable String param) {
        return param;
    }

    public static final StringConverter STRING_CONVERTER = new StringConverter();
}
