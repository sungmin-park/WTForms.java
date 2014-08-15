package kr.redo.wtforms;

import kr.redo.wtforms.fields.TextField;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertEquals;

public class FormTest {
    @Test
    public void linkClassFieldNameToFieldName() throws Exception {
        TestForm testForm = Form.bind(TestForm.class);
        assertEquals("wtf-field", testForm.getField().getParameterName());
    }

    @Test
    public void processData() throws Exception {
        final TestForm testForm = Form.bind(TestForm.class);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        testForm.wtfProcessData(request);
        assertEquals(null, testForm.getField().getValue());

        request.setParameter("wtf-field", "value");
        testForm.wtfProcessData(request);
        assertEquals("value", testForm.getField().getValue());
    }

    @Test
    public void renderField() throws Exception {
        final TestForm testForm = Form.bind(TestForm.class);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        testForm.wtfProcessData(request);
        assertEquals(
                "<input type=\"text\" id=\"wtf-field\" name=\"wtf-field\"/>",
                testForm.getField().toString()
        );
        request.setParameter("wtf-field", "value");
        testForm.wtfProcessData(request);
        assertEquals(
                "<input type=\"text\" id=\"wtf-field\" name=\"wtf-field\" value=\"value\"/>",
                testForm.getField().toString()
        );
    }

    public static class TestForm extends Form {
        @NotNull
        private TextField field = new TextField();

        @NotNull
        public TextField getField() {
            return field;
        }
    }
}
