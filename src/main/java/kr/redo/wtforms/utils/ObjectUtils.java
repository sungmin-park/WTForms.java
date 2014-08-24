package kr.redo.wtforms.utils;

import java.util.Optional;

public class ObjectUtils {
    public static <T> T get(Optional<T> optional) {
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
