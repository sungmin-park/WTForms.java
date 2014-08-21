package kr.redo.wtforms.fields;

import java.util.Optional;

public class ValueField extends Field{
    public Optional<String> getValue() {
        if (values.length < 1) {
            return Optional.empty();
        }
        return Optional.of(values[0]);
    }
}
