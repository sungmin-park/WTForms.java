package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.CheckBoxStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;

public class MultipleValueOptionsValidatorTest {

    @Test
    public void testValidate() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        form.validate();
        Assert.assertFalse(form.hasErrors());
        final CheckBoxStringField checkBoxStringField = form.getCheckBoxStringField();
        checkBoxStringField.setOptions(Arrays.asList("1", "2"));
        Assert.assertFalse(form.hasErrors());
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-check-box-string-field", "-1");
        form.processData(request);
        Assert.assertFalse(form.validate());
        Assert.assertEquals(Arrays.asList("not a valid choice"), checkBoxStringField.getErrors());
    }

    public static class TestForm extends Form {
        private CheckBoxStringField checkBoxStringField = new CheckBoxStringField();

        public CheckBoxStringField getCheckBoxStringField() {
            return checkBoxStringField;
        }
    }
}