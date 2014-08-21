package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertArrayEquals;

public class MultipleValuesFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final MultipleValuesFieldTestForm form = Form.bind(MultipleValuesFieldTestForm.class);
        final MultipleValuesField multipleValuesField = form.getMultipleValuesField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // if there no data, should return empty array
        multipleValuesField.processData(request);
        assertArrayEquals(new String[]{}, multipleValuesField.getValues());
        // should return single value
        request.addParameter("wtf-multiple-values-field", "value");
        multipleValuesField.processData(request);
        assertArrayEquals(new String[]{"value"}, multipleValuesField.getValues());
        // should return more then one value
        request.addParameter("wtf-multiple-values-field", "value two");
        multipleValuesField.processData(request);
        assertArrayEquals(new String[]{"value", "value two"}, multipleValuesField.getValues());
    }

    public static class MultipleValuesFieldTestForm extends Form {
        private MultipleValuesField multipleValuesField = new MultipleValuesField();

        public MultipleValuesField getMultipleValuesField() {
            return multipleValuesField;
        }
    }

}