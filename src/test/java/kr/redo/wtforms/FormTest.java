package kr.redo.wtforms;

import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.fields.Field;
import kr.redo.wtforms.fields.IntegerField;
import kr.redo.wtforms.fields.TextField;
import kr.redo.wtforms.widget.TextWidget;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.HashMap;

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
        assertEquals(null, testForm.getIntegerField().getValue());
        assertEquals(null, testForm.getText().getValue());
        assertEquals("default", testForm.getHasDefault().getValue());
        assertEquals((Integer) 1, testForm.getHasDefaultInteger().getValue());

        request.setParameter("wtf-field", "value");
        request.setParameter("wtf-integer-field", "1");
        testForm.wtfProcessData(request);
        assertEquals("value", testForm.getField().getValue());
        assertEquals((Integer) 1, testForm.getIntegerField().getValue());

        request.setParameter("wtf-integer-field", "not a number");
        testForm.wtfProcessData(request);
        assertEquals((Integer) 1, testForm.getIntegerField().getValue());
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
        private IntegerField integerField = new IntegerField();

        @NotNull
        private Field<String, StringConverter, TextWidget> text = new Field<>(new StringConverter(), new TextWidget());

        @NotNull
        private TextField hasDefault = new TextField("default");

        @NotNull
        private IntegerField hasDefaultInteger = new IntegerField(1);

        @NotNull
        public TextField getField() {
            return field;
        }

        @NotNull
        public IntegerField getIntegerField() {
            return integerField;
        }

        @NotNull
        public Field<String, StringConverter, TextWidget> getText() {
            return text;
        }

        @NotNull
        public TextField getHasDefault() {
            return hasDefault;
        }

        @NotNull
        public IntegerField getHasDefaultInteger() {
            return hasDefaultInteger;
        }
    }
}
