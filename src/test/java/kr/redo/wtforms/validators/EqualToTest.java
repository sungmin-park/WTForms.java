package kr.redo.wtforms.validators;

import kr.redo.wtforms.fields.TextStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class EqualToTest {

    @Test
    public void testValidate() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final TextStringField equalTo = form.getEqualTo();
        final TextStringField target = form.getTarget();
        form.validate();
        assertEquals(Arrays.<String>asList(), equalTo.getErrors());

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-target", "value");
        form.processData(request);
        form.validate();
        assertEquals(Arrays.asList("field should equal."), equalTo.getErrors());

        equalTo.getErrors().removeIf(i -> true);
        request.addParameter("wtf-equal-to", "value");
        form.processData(request);
        form.validate();
        assertEquals(Arrays.<String>asList(), equalTo.getErrors());
        assertEquals(Arrays.<String>asList(), target.getErrors());
    }

    public static class TestForm extends Form {
        private TextStringField target = new TextStringField();
        private TextStringField equalTo = new TextStringField(new EqualTo<>(target));

        public TextStringField getTarget() {
            return target;
        }

        public TextStringField getEqualTo() {
            return equalTo;
        }
    }
}