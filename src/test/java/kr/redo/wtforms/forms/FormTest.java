package kr.redo.wtforms.forms;

import kr.redo.wtforms.fields.Field;
import kr.redo.wtforms.fields.TextStringField;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;
import static kr.redo.wtforms.validators.NotNull.STRING_NOT_NULL;
import static org.junit.Assert.*;

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
        assertEquals(Arrays.asList("Field should not null."), form.getErrors().get("wtf-text-string-field"));

        form = Form.bind(TestForm.class);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-string-field", "value");
        form.processData(request);
        assertTrue(form.validate());
        assertEquals(new HashMap<String, List<String>>(), form.getErrors());
    }


    @Test
    public void testInlineValidation() throws Exception {
        InlineValidateForm form = Form.bind(InlineValidateForm.class);
        assertFalse(form.validate());
        assertEquals(Arrays.asList("inline error"), form.getTextStringField().getErrors());
    }

    public static class SimpleForm extends Form {
        private Field<String> first = new Field<>(STRING_TRANSFORMER);

        public Field<String> getFirst() {
            return first;
        }
    }

    public static class TestForm extends Form {
        private TextStringField textStringField = new TextStringField(STRING_NOT_NULL);

        @SuppressWarnings("UnusedDeclaration")
        public TextStringField getTextStringField() {
            return textStringField;
        }
    }

    public static class InlineValidateForm extends Form {
        private TextStringField textStringField = new TextStringField(f -> f.addError("inline error"));

        public TextStringField getTextStringField() {
            return textStringField;
        }
    }
}