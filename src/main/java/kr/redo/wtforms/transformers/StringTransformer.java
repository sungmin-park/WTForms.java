package kr.redo.wtforms.transformers;

import java.util.Optional;

public class StringTransformer extends ValueTransformer<String> {
    @Override
    public java.util.Optional<String> fromParameterValue(String value){
        return Optional.of(value);
    }

    public static final StringTransformer STRING_TRANSFORMER = new StringTransformer();
}
