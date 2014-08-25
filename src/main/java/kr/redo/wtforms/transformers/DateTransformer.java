package kr.redo.wtforms.transformers;

import org.joda.time.DateMidnight;

import java.util.Optional;

public class DateTransformer extends ValueTransformer<DateMidnight>{
    @Override
    public Optional<DateMidnight> fromParameterValue(String value) {
        return Optional.of(new DateMidnight(value));
    }

    @Override
    public String toParameterValue(DateMidnight dateMidnight) {
        return dateMidnight.toString("yyyy-MM-dd");
    }

    public final static DateTransformer DATE_TRANSFORMER = new DateTransformer();
}
