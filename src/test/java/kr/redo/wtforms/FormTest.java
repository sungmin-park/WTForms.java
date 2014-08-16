package kr.redo.wtforms;

import com.google.common.collect.ImmutableMap;
import kr.redo.wtforms.converters.StringConverter;
import kr.redo.wtforms.fields.Field;
import kr.redo.wtforms.fields.IntegerField;
import kr.redo.wtforms.fields.TextAreaField;
import kr.redo.wtforms.fields.TextField;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static kr.redo.wtforms.converters.StringConverter.STRING_CONVERTER;
import static kr.redo.wtforms.widget.TextWidget.TEXT_WIDGET;
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
        assertEquals(
                ImmutableMap.of(
                        "wtf-field", "value", "wtf-integer-field", "1", "wtf-has-default", "default",
                        "wtf-has-default-integer", "1"
                ),
                testForm.getParameterMap()
        );

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
        assertEquals("<textarea id=\"wtf-text-area\" name=\"wtf-text-area\"></textarea>", testForm.getTextArea().toString());
        request.setParameter("wtf-field", "value");
        request.setParameter("wtf-text-area", "<h1>test</h1>");
        testForm.wtfProcessData(request);
        assertEquals(
                "<input type=\"text\" id=\"wtf-field\" name=\"wtf-field\" value=\"value\"/>",
                testForm.getField().toString()
        );
        assertEquals(
                "<textarea id=\"wtf-text-area\" name=\"wtf-text-area\">&lt;h1&gt;test&lt;/h1&gt;</textarea>",
                testForm.getTextArea().toString()
        );

    }

    public static class TestForm extends Form {
        @NotNull
        private TextField field = new TextField();

        @NotNull
        private IntegerField integerField = new IntegerField();

        @NotNull
        private Field<String, StringConverter> text = new Field<>(STRING_CONVERTER, TEXT_WIDGET);

        @NotNull
        private TextField hasDefault = new TextField("default");

        @NotNull
        private IntegerField hasDefaultInteger = new IntegerField(1);

        @NotNull
        private TextAreaField textArea = new TextAreaField();

        @NotNull
        public TextField getField() {
            return field;
        }

        @NotNull
        public IntegerField getIntegerField() {
            return integerField;
        }

        @NotNull
        public Field<String, StringConverter> getText() {
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

        @NotNull
        public TextAreaField getTextArea() {
            return textArea;
        }
    }
}
