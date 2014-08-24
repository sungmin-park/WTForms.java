package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import org.joda.time.DateMidnight;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.Assert.*;

public class DateFieldTest {
    @Test
    public void testProcessData() throws InstantiationException, IllegalAccessException {
        final DateFieldTestForm form = Form.bind(DateFieldTestForm.class);
        final DateField dateField = form.getDateField();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        dateField.processData(request);
        assertEquals(Optional.<DateMidnight>empty(), dateField.getValue());
        // test default date process
        final DateMidnight today = new DateMidnight();
        request.addParameter("wtf-date-field", today.toString("yyyy-MM-dd"));
        dateField.processData(request);
        assertEquals(Optional.of(today), dateField.getValue());
    }

    public static class DateFieldTestForm extends Form {
        public DateField getDateField() {
            return dateField;
        }

        private DateField dateField = new DateField();
    }
}