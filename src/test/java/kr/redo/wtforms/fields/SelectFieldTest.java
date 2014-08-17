package kr.redo.wtforms.fields;

import kr.redo.wtforms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertEquals;

public class SelectFieldTest {
    @Test
    public void testBinding() throws Exception {
        final SelectForm form = Form.bind(SelectForm.class);
        final SelectField selectField = form.getSelectField();
        assertEquals(null, selectField.getValue());
        final MockHttpServletRequest request = new MockHttpServletRequest();
        // without options value will not touched
        request.setParameter("wtf-select-field", "x");
        form.wtfProcessData(request);
        assertEquals(null, selectField.getValue());
        //  or wrong value still rejected
        selectField.setOptions(new String[]{"a", "b", "c"});
        form.wtfProcessData(request);
        assertEquals(null, selectField.getValue());
        // value will processed only options are allowed
        request.setParameter("wtf-select-field", "b");
        form.wtfProcessData(request);
        assertEquals("b", selectField.getValue());
    }

    @Test
    public void testRender() throws Exception {
        final SelectForm form = Form.bind(SelectForm.class);
        final SelectField selectField = form.getSelectField();
        assertEquals(
                "<select id=\"wtf-select-field\" name=\"wtf-select-field\"></select>",
                selectField.toString()
        );
        selectField.setOptions(new String[]{"a", "b", "c"});
        assertEquals(
                "<select id=\"wtf-select-field\" name=\"wtf-select-field\">" +
                        "<option value=\"a\">a</option>" +
                        "<option value=\"b\">b</option>" +
                        "<option value=\"c\">c</option>" +
                        "</select>",
                selectField.toString()
        );
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("wtf-select-field", "b");
        form.wtfProcessData(request);
        assertEquals(
                "<select id=\"wtf-select-field\" name=\"wtf-select-field\">" +
                        "<option value=\"a\">a</option>" +
                        "<option value=\"b\" selected=\"true\">b</option>" +
                        "<option value=\"c\">c</option>" +
                        "</select>",
                selectField.toString()
        );
    }

    public static class SelectForm extends Form {
        private SelectField selectField = new SelectField();

        public SelectField getSelectField() {
            return selectField;
        }
    }

}