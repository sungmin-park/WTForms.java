package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

public class MultipleValueOptionsFieldTest {

    @Test
    public void testProcessData() throws Exception {
        final MultipleValueOptionsFieldTestForm form = Form.bind(MultipleValueOptionsFieldTestForm.class);
        final MultipleValueOptionsField field = form.getMultipleValueOptionsField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // should return empty array when no values
        field.processData(request);
        assertArrayEquals(new String[]{}, field.getValues());
        // should not take value when value is not in options
        request.addParameter("wtf-multiple-value-options-field", "one");
        field.processData(request);
        assertArrayEquals(new String[]{}, field.getValues());
        // take value when it in options
        field.setOptions(new String[] {"one"});
        field.processData(request);
        assertArrayEquals(new String[]{"one"}, field.getValues());
        // take value if and only if in options
        request.addParameter("wtf-multiple-value-options-field", "two");
        field.processData(request);
        assertArrayEquals(new String[]{"one"}, field.getValues());
    }

    public static class MultipleValueOptionsFieldTestForm extends Form {
        public MultipleValueOptionsField getMultipleValueOptionsField() {
            return multipleValueOptionsField;
        }

        private MultipleValueOptionsField multipleValueOptionsField = new MultipleValueOptionsField();
    }
}