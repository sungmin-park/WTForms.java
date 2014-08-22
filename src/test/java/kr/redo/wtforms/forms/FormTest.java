package kr.redo.wtforms.forms;

import kr.redo.wtforms.fields.AbstractField;
import kr.redo.wtforms.fields.Field;
import kr.redo.wtforms.transformers.StringTransformer;
import org.junit.Assert;
import org.junit.Test;

public class FormTest {
    @Test
    public void bindWithSubClass() throws InstantiationException, IllegalAccessException {
        final SimpleForm form = Form.bind(SimpleForm.class);
        final AbstractField first = form.getFirst();
        Assert.assertEquals(first.getName(), "first");
        Assert.assertEquals(first.getParameterName(), "wtf-first");
    }

    public static class SimpleForm extends Form {
        private Field<String> first = new Field<>(StringTransformer.STRING_TRANSFORMER);

        public Field<String> getFirst() {
            return first;
        }
    }
}