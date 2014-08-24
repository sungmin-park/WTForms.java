package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.Field;

public interface Validator<T> {
    public void validate(Field<T> field) throws Exception;
}
