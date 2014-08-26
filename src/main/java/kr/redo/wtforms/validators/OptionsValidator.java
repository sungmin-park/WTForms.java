package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.OptionsField;

@FunctionalInterface
public interface OptionsValidator<T> {
    public void validate(OptionsField<T> field) throws Exception;
}