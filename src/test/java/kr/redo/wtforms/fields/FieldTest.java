package kr.redo.wtforms.fields;

import junit.framework.Assert;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

public class FieldTest {
    @Test
    public void testValue() throws InstantiationException, IllegalAccessException {
        final FieldTestForm form = Form.bind(FieldTestForm.class);
        final Field valueField = form.getField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // if there is no value return null option
        valueField.processData(request);
        Assert.assertEquals(Optional.<String>empty(), valueField.getValue());
        request.addParameter("wtf-field", "value");
        valueField.processData(request);
        Assert.assertEquals(Optional.of("value"), valueField.getValue());
    }

    public static class FieldTestForm extends Form {
        private Field field = new Field();

        public Field getField() {
            return field;
        }
    }
}