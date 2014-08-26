package kr.redo.wtforms.widgets;

import junit.framework.Assert;
import kr.redo.wtforms.fields.SelectStringField;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class SelectWidgetTest {
    @Test
    public void testRender() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final SelectStringField selectStringField = form.getSelectStringField();
        Assert.assertEquals(
                "<select id=\"wtf-select-string-field\" name=\"wtf-select-string-field\"></select>", selectStringField.render()
        );
        selectStringField.setOptions(new String[]{"one", "two"});
        Assert.assertEquals(
                "<select id=\"wtf-select-string-field\" name=\"wtf-select-string-field\">" +
                        "<option value=\"one\">one</option>" +
                        "<option value=\"two\">two</option>" +
                        "</select>",
                selectStringField.render()
        );
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-select-string-field", "two");
        selectStringField.processData(request);
        Assert.assertEquals(
                "<select id=\"wtf-select-string-field\" name=\"wtf-select-string-field\">" +
                        "<option value=\"one\">one</option>" +
                        "<option value=\"two\" selected=\"selected\">two</option>" +
                        "</select>",
                selectStringField.render()
        );
    }

    public static class TestForm extends Form {
        private SelectStringField selectStringField = new SelectStringField();

        public SelectStringField getSelectStringField() {
            return selectStringField;
        }
    }
}