package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.CheckBoxStringField;
import kr.redo.wtforms.fields.TextStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static java.util.Arrays.asList;
import static kr.redo.wtforms.validators.NotNull.STRING_NOT_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class NotNullTest {

    @Test
    public void testValidate() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final TextStringField textStringField = form.getTextStringField();
        final CheckBoxStringField checkBoxStringField = form.getCheckBoxStringField();
        form.validate();
        assertEquals(asList("Field should not null."), textStringField.getErrors());
        assertEquals(asList("Field should not empty"), checkBoxStringField.getErrors());
        form.clearErrors();

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-string-field", "");
        request.addParameter("wtf-check-box-string-field", "value");
        checkBoxStringField.setOptions(asList("value"));
        form.processData(request);
        form.validate();
        assertFalse(textStringField.hasErrors());
        assertFalse(checkBoxStringField.hasErrors());
    }

    public static class TestForm extends Form {
        private final TextStringField textStringField = new TextStringField(STRING_NOT_NULL);
        private final CheckBoxStringField checkBoxStringField = new CheckBoxStringField(STRING_NOT_NULL);

        public CheckBoxStringField getCheckBoxStringField() {
            return checkBoxStringField;
        }

        public TextStringField getTextStringField() {
            return textStringField;
        }
    }
}