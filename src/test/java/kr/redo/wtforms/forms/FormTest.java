package kr.redo.wtforms.forms;

import kr.redo.wtforms.fields.Field;
import kr.redo.wtforms.fields.TextStringField;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;
import static kr.redo.wtforms.validators.NotNull.STRING_NOT_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FormTest {
    @Test
    public void bindWithSubClass() throws InstantiationException, IllegalAccessException {
        final SimpleForm form = Form.bind(SimpleForm.class);
        final Field<String> first = form.getFirst();
        assertEquals(first.getName(), "first");
        assertEquals(first.getParameterName(), "wtf-first");
        final MockHttpServletRequest request = new MockHttpServletRequest();
        form.processData(request);
        assertEquals(Optional.<String>empty(), first.getValue());
        request.addParameter("wtf-first", "value");
        form.processData(request);
        assertEquals(Optional.of("value"), first.getValue());
    }

    @Test
    public void testValidate() throws Exception {
        TestForm form = Form.bind(TestForm.class);
        assertFalse(form.validate());

        form = Form.bind(TestForm.class);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-string-field", "value");
        form.processData(request);
        assertTrue(form.validate());
    }

    public static class SimpleForm extends Form {
        private Field<String> first = new Field<>(STRING_TRANSFORMER);

        public Field<String> getFirst() {
            return first;
        }
    }

    public static class TestForm extends Form {
        private TextStringField textStringField = new TextStringField(STRING_NOT_NULL);

        public TextStringField getTextStringField() {
            return textStringField;
        }
    }
}