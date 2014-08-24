package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.TextField;
import kr.redo.wtforms.forms.Form;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class TextWidgetTest {
    @Test
    public void testRender() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final TextField textField = form.getTextField();
        Assert.assertEquals(
                "<input type=\"text\" id=\"wtf-text-field\" name=\"wtf-text-field\"/>", textField.render()
        );
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-field", "<value>");
        textField.processData(request);
        Assert.assertEquals(
                "<input type=\"text\" id=\"wtf-text-field\" name=\"wtf-text-field\" value=\"&lt;value&gt;\"/>",
                textField.render()
        );
    }

    public static class TestForm extends Form {
        private TextField textField = new TextField();

        public TextField getTextField() {
            return textField;
        }
    }
}