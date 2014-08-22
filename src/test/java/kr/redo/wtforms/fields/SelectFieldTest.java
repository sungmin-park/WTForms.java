package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.Assert.*;

public class SelectFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final SelectFieldTestForm form = Form.bind(SelectFieldTestForm.class);
        final SelectField selectField = form.getSelectField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // return empty
        selectField.processData(request);
        assertEquals(Optional.<String>empty(), selectField.getValue());
        // parameter not in options, will be ignored.
        request.addParameter("wtf-select-field", "value");
        selectField.processData(request);
        assertEquals(Optional.<String>empty(), selectField.getValue());
        // parameter in options, will be accepted.
        selectField.setOptions(new String[]{"value"});
        selectField.processData(request);
        assertEquals(Optional.of("value"), selectField.getValue());
    }

    public static class SelectFieldTestForm extends Form {
        private SelectField selectField = new SelectField();

        public SelectField getSelectField() {
            return selectField;
        }
    }
}