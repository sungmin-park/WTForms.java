package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.MultipleValueOptionsField;

@FunctionalInterface
public interface MultipleValueOptionsValidator<T> {
    public void validate(MultipleValueOptionsField<T> Field) throws Exception;
}
