package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.Field;

@FunctionalInterface
public interface Validator<T> {
    public void validate(Field<T> field) throws Exception;
}
