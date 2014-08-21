package kr.redo.wtforms.fields;

import junit.framework.Assert;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

public class ValueFieldTest {
    @Test
    public void testValue() throws InstantiationException, IllegalAccessException {
        final ValueFieldTestForm form = Form.bind(ValueFieldTestForm.class);
        final ValueField valueField = form.getValueField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // if there is no value return null option
        valueField.processData(request);
        Assert.assertEquals(Optional.<String>empty(), valueField.getValue());
        request.addParameter("wtf-value-field", "value");
        valueField.processData(request);
        Assert.assertEquals(Optional.of("value"), valueField.getValue());
    }

    public static class ValueFieldTestForm extends Form {
        private ValueField valueField = new ValueField();

        public ValueField getValueField() {
            return valueField;
        }
    }
}