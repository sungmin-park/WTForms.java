package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import kr.redo.wtforms.validators.NotNull;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Arrays.asList;
import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;
import static org.junit.Assert.assertEquals;

public class OptionsFieldTest {
    @Test
    public void testProcessData() throws Exception {
        final OptionsFieldTestForm form = Form.bind(OptionsFieldTestForm.class);
        final OptionsField<String> optionsField = form.getOptionsField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        optionsField.validate();
        assertEquals(asList("Field should not null."), optionsField.getErrors());
        optionsField.clearErrors();

        request.addParameter("wtf-options-field", "value");
        optionsField.processData(request);
        optionsField.validate();
        assertEquals(Optional.<String>empty(), optionsField.getValue());
        assertEquals(asList("not a valid choice."), optionsField.getErrors());
        optionsField.clearErrors();

        optionsField.setOptions(new String[]{"value"});
        optionsField.processData(request);
        assertEquals(Optional.of("value"), optionsField.getValue());
        assertEquals(Arrays.<String>asList(), optionsField.getErrors());
    }

    public static class OptionsFieldTestForm extends Form {
        private SelectField<String> optionsField = new SelectField<String>(STRING_TRANSFORMER, NotNull.STRING_NOT_NULL);

        public SelectField<String> getOptionsField() {
            return optionsField;
        }
    }
}