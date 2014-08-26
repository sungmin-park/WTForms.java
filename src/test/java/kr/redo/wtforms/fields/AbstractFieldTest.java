package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AbstractFieldTest {

    @Test
    public void testGetTag() throws Exception {
        final TestForm form = Form.bind(TestForm.class);
        assertEquals(
                "<input id=\"wtf-text-string-field\" name=\"wtf-text-string-field\" type=\"text\"/>",
                form.getTextStringField().getTag()
        );
    }

    public static class TestForm extends Form {
        TextStringField textStringField = new TextStringField();

        public TextStringField getTextStringField() {
            return textStringField;
        }
    }
}