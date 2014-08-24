package kr.redo.wtforms.fields;

import org.joda.time.DateMidnight;

import static kr.redo.wtforms.transformers.DateTransformer.DATE_TRANSFORMER;

public class DateField extends Field<DateMidnight> {
    public DateField() {
        super(DATE_TRANSFORMER);
    }
}
