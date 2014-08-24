package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.Field;

public class NotNull<T> extends AbstractValidator<T> implements Validator<T> {

    @Override
    public void validate(Field<T> field) throws Exception {
        if (field.getValue().isPresent()) {
            return;
        }
        field.addError("Field should not null.");
    }

    public static final NotNull<String> STRING_NOT_NULL = new NotNull<>();
}
