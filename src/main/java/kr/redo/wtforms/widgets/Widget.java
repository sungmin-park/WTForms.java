package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.Field;

public interface Widget {
    public String render(Field<?> field) throws Exception;
}
