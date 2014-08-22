package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IntegerFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final IntegerFieldTestForm form = Form.bind(IntegerFieldTestForm.class);
        final IntegerField field = form.getIntegerField();
        // should return empty if there are no values
        final MockHttpServletRequest request = new MockHttpServletRequest();
        field.processData(request);
        assertEquals(Optional.<Integer>empty(), field.getValue());
        // should return empty if value is not integer
        request.addParameter("wtf-integer-field", "this is not a integer");
        field.processData(request);
        assertEquals(Optional.<Integer>empty(), field.getValue());
        // should return 1
        request.setParameter("wtf-integer-field", "1");
        field.processData(request);
        assertEquals(Optional.of(1), field.getValue());
    }

    public static class IntegerFieldTestForm extends Form {
        private IntegerField integerField = new IntegerField();

        public IntegerField getIntegerField() {
            return integerField;
        }
    }
}