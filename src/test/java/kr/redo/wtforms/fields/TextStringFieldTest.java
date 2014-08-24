package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TextStringFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final TextFieldTestForm form = Form.bind(TextFieldTestForm.class);
        final TextStringField textStringField = form.getTextStringField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-string-field", "textValue");
        textStringField.processData(request);
        assertEquals(Optional.of("textValue"), textStringField.getValue());
    }

    public static class TextFieldTestForm extends Form {
        private TextStringField textStringField = new TextStringField();

        public TextStringField getTextStringField() {
            return textStringField;
        }
    }
}