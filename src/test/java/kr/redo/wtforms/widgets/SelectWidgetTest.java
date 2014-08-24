package kr.redo.wtforms.widgets;

import junit.framework.Assert;
import kr.redo.wtforms.fields.SelectField;
import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class SelectWidgetTest {
    @Test
    public void testRender() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        final SelectField selectField = form.getSelectField();
        Assert.assertEquals(
                "<select id=\"wtf-select-field\" name=\"wtf-select-field\"></select>", selectField.render()
        );
        selectField.setOptions(new String[]{"one", "two"});
        Assert.assertEquals(
                "<select id=\"wtf-select-field\" name=\"wtf-select-field\">" +
                        "<option value=\"one\">one</option>" +
                        "<option value=\"two\">two</option>" +
                        "</select>",
                selectField.render()
        );
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-select-field", "two");
        selectField.processData(request);
        Assert.assertEquals(
                "<select id=\"wtf-select-field\" name=\"wtf-select-field\">" +
                        "<option value=\"one\">one</option>" +
                        "<option value=\"two\" selected=\"selected\">two</option>" +
                        "</select>",
                selectField.render()
        );
    }

    public static class TestForm extends Form {
        private SelectField selectField = new SelectField();

        public SelectField getSelectField() {
            return selectField;
        }
    }
}