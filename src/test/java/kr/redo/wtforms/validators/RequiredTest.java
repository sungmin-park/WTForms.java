package kr.redo.wtforms.validators;

import junit.framework.Assert;
import kr.redo.wtforms.fields.TextStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static kr.redo.wtforms.validators.Required.REQUIRED;

public class RequiredTest {

    @Test
    public void testValidate() throws Exception {

        TestForm form = Form.bind(TestForm.class);
        Assert.assertFalse("Required should be false on null", form.validate());

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("wtf-field", "");
        form = Form.bind(TestForm.class);
        form.processData(request);
        Assert.assertFalse("Required should be false on empty", form.validate());

        request.setParameter("wtf-field", " ");
        form = Form.bind(TestForm.class);
        form.processData(request);
        final boolean validate = form.validate();
        Assert.assertEquals(Optional.of(""), form.getField().getValue());
        Assert.assertFalse("Required should be false on trimmed", validate);

        request.setParameter("wtf-field", "value");
        form = Form.bind(TestForm.class);
        form.processData(request);
        Assert.assertTrue("Required should be true on correct value", form.validate());
    }

    public static class TestForm extends Form {
        private TextStringField field = new TextStringField(REQUIRED);

        public TextStringField getField() {
            return field;
        }
    }
}