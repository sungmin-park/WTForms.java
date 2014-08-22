package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;
import static org.junit.Assert.assertEquals;

public class OptionsFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final OptionsFieldTestForm form = Form.bind(OptionsFieldTestForm.class);
        final OptionsField<String> optionsField = form.getOptionsField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("wtf-options-field", "value");
        optionsField.processData(request);
        assertEquals(Optional.<String>empty(), optionsField.getValue());
        optionsField.setOptions(new String[]{"value"});
        optionsField.processData(request);
        assertEquals(Optional.of("value"), optionsField.getValue());
    }

    public static class OptionsFieldTestForm extends Form {
        private OptionsField<String> optionsField = new OptionsField<>(STRING_TRANSFORMER);

        public OptionsField<String> getOptionsField() {
            return optionsField;
        }
    }
}