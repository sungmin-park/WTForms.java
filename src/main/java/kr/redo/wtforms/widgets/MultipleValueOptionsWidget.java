package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.MultipleValueOptionsField;

public interface MultipleValueOptionsWidget {
    public String render(MultipleValueOptionsField<?> field) throws Exception;
}
