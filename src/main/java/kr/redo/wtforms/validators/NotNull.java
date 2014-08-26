package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.Field;
import kr.redo.wtforms.fields.MultipleValueOptionsField;

public class NotNull<T> extends AbstractValidator<T> implements Validator<T>, MultipleValueOptionsValidator<T> {

    public static final NotNull<String> STRING_NOT_NULL = new NotNull<>();

    @Override
    public void validate(Field<T> field) throws Exception {
        if (field.getValue().isPresent()) {
            return;
        }
        field.addError("Field should not null.");
    }

    @Override
    public void validate(MultipleValueOptionsField<T> field) throws Exception {
        if (field.getValues().size() > 0) {
            return;
        }
        field.addError("Field should not empty");
    }
}
