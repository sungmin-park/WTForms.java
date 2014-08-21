package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

public class FieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final FieldTestForm form = Form.bind(FieldTestForm.class);
        // if there is no data for a field, should return empty array
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final Field field = form.getField();
        field.processData(request);
        assertArrayEquals(field.getValues(), new String[]{});
        // if there is a data for a field should return values
        request.addParameter("wtf-field", "value");
        field.processData(request);
        assertArrayEquals(field.getValues(), new String[]{"value"});
        // if matched values more than one, then takes all of them.
        request.addParameter("wtf-field", "value1");
        field.processData(request);
        assertArrayEquals(field.getValues(), new String[]{"value", "value1"});
        // if values are duplicated, just store it.
        request.addParameter("wtf-field", "value");
        field.processData(request);
        assertArrayEquals(field.getValues(), new String[]{"value", "value1", "value"});
    }

    public static class FieldTestForm extends Form {
        public Field getField() {
            return field;
        }

        Field field = new Field();
    }
}