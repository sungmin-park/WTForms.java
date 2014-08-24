package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.Field;

import java.util.Optional;

public class EqualTo<T> extends AbstractValidator<T> implements Validator<T> {
    private Field<T> targetField;

    public EqualTo(Field<T> targetField) {
        this.targetField = targetField;
    }

    @Override
    public void validate(Field<T> field) throws Exception {
        final Optional<T> value = field.getValue();
        final Optional<T> targetFieldValue = targetField.getValue();
        if (!value.isPresent() && !targetFieldValue.isPresent()) {
            return;
        }
        if (!value.isPresent() || !value.equals(targetFieldValue)) {
            field.addError("field should equal.");
        }
    }
}
