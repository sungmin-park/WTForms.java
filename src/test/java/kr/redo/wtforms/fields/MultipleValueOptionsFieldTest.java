package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import kr.redo.wtforms.transformers.StringTransformer;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class MultipleValueOptionsFieldTest {

    @Test
    public void testProcessData() throws Exception {
        final MultipleValueOptionsFieldTestForm form = Form.bind(MultipleValueOptionsFieldTestForm.class);
        final MultipleValueOptionsField<String> field = form.getMultipleValueOptionsField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // should return empty array when no values
        field.processData(request);
        assertEquals(new ArrayList<String>(), field.getValues());
        // should not take value when value is not in options
        request.addParameter("wtf-multiple-value-options-field", "one");
        field.processData(request);
        assertEquals(new ArrayList<String>(), field.getValues());
        // take value when it in options
        field.setOptions(asList("one"));
        field.processData(request);
        assertEquals(asList("one"), field.getValues());
        // take value if and only if in options
        request.addParameter("wtf-multiple-value-options-field", "two");
        field.processData(request);
        assertEquals(asList("one"), field.getValues());
        // take more then one value
        request.addParameter("wtf-multiple-value-options-field", "three");
        field.setOptions(asList("one", "three"));
        field.processData(request);
        assertEquals(asList("one", "three"), field.getValues());
    }

    public static class MultipleValueOptionsFieldTestForm extends Form {
        private MultipleValueOptionsField<String> multipleValueOptionsField =
                new MultipleValueOptionsField<>(StringTransformer.STRING_TRANSFORMER);

        public MultipleValueOptionsField<String> getMultipleValueOptionsField() {
            return multipleValueOptionsField;
        }
    }
}