package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.Field;

import java.util.Optional;

public class Required extends AbstractValidator<String> implements Validator<String> {
    @Override
    public void validate(Field<String> field) throws Exception {
        final Optional<String> value = field.getValue().map(String::trim);
        field.setValue(value);
        if (value.isPresent() && value.get().length() > 0) {
            return;
        }
        field.addError("value is required.");
    }

    public static final Required REQUIRED = new Required();
}
