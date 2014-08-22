package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TextFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final TextFieldTestForm form = Form.bind(TextFieldTestForm.class);
        final TextField textField = form.getTextField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-text-field", "textValue");
        textField.processData(request);
        assertEquals(Optional.of("textValue"), textField.getValue());
    }

    public static class TextFieldTestForm extends Form {
        private TextField textField = new TextField();

        public TextField getTextField() {
            return textField;
        }
    }
}