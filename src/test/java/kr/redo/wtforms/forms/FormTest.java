package kr.redo.wtforms.forms;

import kr.redo.wtforms.fields.Field;
import org.junit.Assert;
import org.junit.Test;

public class FormTest {
    @Test
    public void BindWithSubClass() throws InstantiationException, IllegalAccessException {
        final SimpleForm form = Form.bind(SimpleForm.class);
        Assert.assertEquals(form.getFirst().getName(), "first");
    }

    public static class SimpleForm extends Form {
        private Field first = new Field();

        public Field getFirst() {
            return first;
        }
    }
}