package kr.redo.wtforms.validators;

import com.google.common.collect.Lists;
import kr.redo.wtforms.fields.TextStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.List;

import static kr.redo.wtforms.validators.NotNull.STRING_NOT_NULL;

public class NotNullTest {

    @Test
    public void testValidate() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final TextStringField textStringField = form.getTextStringField();
        textStringField.validate();
        final List<String> errors = textStringField.getErrors();
        Assert.assertEquals(Lists.newArrayList("Field should not null."), errors);
        errors.removeIf(i -> true);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-string-field", "");
        textStringField.processData(request);
        textStringField.validate();
        Assert.assertFalse(textStringField.hasErrors());

    }

    public static class TestForm extends Form {
        private final TextStringField textStringField = new TextStringField(STRING_NOT_NULL);

        public TextStringField getTextStringField() {
            return textStringField;
        }
    }
}