package kr.redo.wtforms.widgets;

import kr.redo.wtforms.fields.RadioStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class RadioWidgetTest {
    @Test
    public void testRender() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final RadioStringField optionsField = form.getOptionsField();
        Assert.assertEquals(
                "<ul class=\"wtf-widget\"></ul>", optionsField.render()
        );
        optionsField.setOptions(new String[]{"one", "two"});
        Assert.assertEquals(
                "<ul class=\"wtf-widget\">" +
                        "<li>" +
                        "<input id=\"wtf-options-field-0\" name=\"wtf-options-field\" type=\"radio\" value=\"one\"/>" +
                        "<label for=\"wtf-options-field-0\">one</label>" +
                        "</li>" +
                        "<li>" +
                        "<input id=\"wtf-options-field-1\" name=\"wtf-options-field\" type=\"radio\" value=\"two\"/>" +
                        "<label for=\"wtf-options-field-1\">two</label>" +
                        "</li>" +
                        "</ul>", optionsField.render()
        );
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-options-field", "two");
        optionsField.processData(request);
        Assert.assertEquals(
                "<ul class=\"wtf-widget\">" +
                        "<li>" +
                        "<input id=\"wtf-options-field-0\" name=\"wtf-options-field\" type=\"radio\" value=\"one\"/>" +
                        "<label for=\"wtf-options-field-0\">one</label>" +
                        "</li>" +
                        "<li>" +
                        "<input id=\"wtf-options-field-1\" name=\"wtf-options-field\" type=\"radio\" value=\"two\" checked=\"checked\"/>" +
                        "<label for=\"wtf-options-field-1\">two</label>" +
                        "</li>" +
                        "</ul>", optionsField.render()
        );
    }

    public static class TestForm extends Form {
        private RadioStringField optionsField = new RadioStringField();

        public RadioStringField getOptionsField() {
            return optionsField;
        }
    }
}