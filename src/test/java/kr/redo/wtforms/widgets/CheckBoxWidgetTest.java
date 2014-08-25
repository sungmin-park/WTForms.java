package kr.redo.wtforms.widgets;

import junit.framework.Assert;
import kr.redo.wtforms.fields.CheckBoxStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;

public class CheckBoxWidgetTest {

    @Test
    public void testRender() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        form.processData(request);
        final CheckBoxStringField checkBoxStringField = form.getCheckBoxStringField();
        Assert.assertEquals("<ul class=\"wtf-widget\"></ul>", checkBoxStringField.render());
        checkBoxStringField.setOptions(Arrays.asList("1", "2", "3"));
        Assert.assertEquals(
                "<ul class=\"wtf-widget\">" +
                        "<li><input id=\"wtf-check-box-string-field-0\" name=\"wtf-check-box-string-field\" type=\"checkbox\" value=\"1\"/><label for=\"wtf-check-box-string-field-0\">1</label></li>" +
                        "<li><input id=\"wtf-check-box-string-field-1\" name=\"wtf-check-box-string-field\" type=\"checkbox\" value=\"2\"/><label for=\"wtf-check-box-string-field-1\">2</label></li>" +
                        "<li><input id=\"wtf-check-box-string-field-2\" name=\"wtf-check-box-string-field\" type=\"checkbox\" value=\"3\"/><label for=\"wtf-check-box-string-field-2\">3</label></li>" +
                        "</ul>",
                checkBoxStringField.render()
        );
        request.addParameter("wtf-check-box-string-field", "1");
        request.addParameter("wtf-check-box-string-field", "3");
        form.processData(request);
        Assert.assertEquals(
                "<ul class=\"wtf-widget\">" +
                        "<li><input id=\"wtf-check-box-string-field-0\" name=\"wtf-check-box-string-field\" type=\"checkbox\" value=\"1\" checked=\"checked\"/><label for=\"wtf-check-box-string-field-0\">1</label></li>" +
                        "<li><input id=\"wtf-check-box-string-field-1\" name=\"wtf-check-box-string-field\" type=\"checkbox\" value=\"2\"/><label for=\"wtf-check-box-string-field-1\">2</label></li>" +
                        "<li><input id=\"wtf-check-box-string-field-2\" name=\"wtf-check-box-string-field\" type=\"checkbox\" value=\"3\" checked=\"checked\"/><label for=\"wtf-check-box-string-field-2\">3</label></li>" +
                        "</ul>",
                checkBoxStringField.render()
        );

    }

    public static class TestForm extends Form {
        private final CheckBoxStringField checkBoxStringField = new CheckBoxStringField();

        public CheckBoxStringField getCheckBoxStringField() {
            return checkBoxStringField;
        }
    }
}