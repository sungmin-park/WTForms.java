package kr.redo.wtforms.fields;

import kr.redo.wtforms.transformers.Transformer;
import kr.redo.wtforms.widgets.RadioWidget;

public class RadioField<T> extends OptionsField<T> {
    public RadioField(Transformer<T> transformer) {
        super(transformer, RadioWidget.RADIO_WIDGET);
    }
}
