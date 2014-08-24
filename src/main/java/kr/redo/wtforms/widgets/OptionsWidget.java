package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.OptionsField;

public interface OptionsWidget {
    public String render(OptionsField<?> field) throws Exception;
}
