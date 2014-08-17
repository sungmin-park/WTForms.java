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

    public static class SelectForm extends Form {
        private SelectField selectField = new SelectField();

        public SelectField getSelectField() {
            return selectField;
        }
    }

}