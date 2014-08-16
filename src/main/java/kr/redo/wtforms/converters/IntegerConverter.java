package kr.redo.wtforms.converters;

import org.jetbrains.annotations.Nullable;

public class IntegerConverter extends Converter<Integer> {
    @Nullable
    @Override
    public Integer fromRequestParam(@Nullable String param) {
        if (param == null) {
            return null;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static final IntegerConverter INTEGER_CONVERTER = new IntegerConverter();
}
