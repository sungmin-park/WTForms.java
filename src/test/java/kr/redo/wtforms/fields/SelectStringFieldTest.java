package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.Assert.*;

public class SelectStringFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final SelectFieldTestForm form = Form.bind(SelectFieldTestForm.class);
        final SelectStringField selectStringField = form.getSelectStringField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // return empty
        selectStringField.processData(request);
        assertEquals(Optional.<String>empty(), selectStringField.getValue());
        // parameter not in options, will be ignored.
        request.addParameter("wtf-select-string-field", "value");
        selectStringField.processData(request);
        assertEquals(Optional.<String>empty(), selectStringField.getValue());
        // parameter in options, will be accepted.
        selectStringField.setOptions(new String[]{"value"});
        selectStringField.processData(request);
        assertEquals(Optional.of("value"), selectStringField.getValue());
    }

    public static class SelectFieldTestForm extends Form {
        private SelectStringField selectStringField = new SelectStringField();

        public SelectStringField getSelectStringField() {
            return selectStringField;
        }
    }
}