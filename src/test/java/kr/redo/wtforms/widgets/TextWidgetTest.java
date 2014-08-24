package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.TextStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class TextWidgetTest {
    @Test
    public void testRender() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final TextStringField textStringField = form.getTextStringField();
        Assert.assertEquals(
                "<input id=\"wtf-text-string-field\" name=\"wtf-text-string-field\" type=\"text\"/>",
                textStringField.render()
        );
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-string-field", "<value>");
        textStringField.processData(request);
        Assert.assertEquals(
                "<input id=\"wtf-text-string-field\" name=\"wtf-text-string-field\" type=\"text\" value=\"&lt;value&gt;\"/>",
                textStringField.render()
        );
    }

    public static class TestForm extends Form {
        private TextStringField textStringField = new TextStringField();

        public TextStringField getTextStringField() {
            return textStringField;
        }
    }
}