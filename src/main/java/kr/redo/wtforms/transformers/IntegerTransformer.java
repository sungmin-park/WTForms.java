package kr.redo.wtforms.transformers;

import java.util.Optional;

public class IntegerTransformer extends Transformer<Integer> {
    @Override
    public java.util.Optional<Integer> fromParameterValue(String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static final IntegerTransformer INTEGER_TRANSFORMER = new IntegerTransformer();
}
