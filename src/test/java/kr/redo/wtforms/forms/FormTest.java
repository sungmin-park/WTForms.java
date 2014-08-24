package kr.redo.wtforms.forms;

import kr.redo.wtforms.fields.Field;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static kr.redo.wtforms.transformers.StringTransformer.STRING_TRANSFORMER;
import static org.junit.Assert.assertEquals;

public class FormTest {
    @Test
    public void bindWithSubClass() throws InstantiationException, IllegalAccessException {
        final SimpleForm form = Form.bind(SimpleForm.class);
        final Field<String> first = form.getFirst();
        assertEquals(first.getName(), "first");
        assertEquals(first.getParameterName(), "wtf-first");
        final MockHttpServletRequest request = new MockHttpServletRequest();
        form.processData(request);
        assertEquals(Optional.<String>empty(), first.getValue());
        request.addParameter("wtf-first", "value");
        form.processData(request);
        assertEquals(Optional.of("value"), first.getValue());
    }

    public static class SimpleForm extends Form {
        private Field<String> first = new Field<>(STRING_TRANSFORMER);

        public Field<String> getFirst() {
            return first;
        }
    }
}