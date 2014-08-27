package kr.redo.wtforms;

import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        Form form = new Form(request, (f) -> f.id.setData(10));
        if (form.validate()) {
            System.err.println("failed");
        }
    }

    public static class Form {
        public final IntegerField id = new IntegerField();

        public Form(HttpServletRequest request, Consumer<Form> initializer) {
            if (request.getAttributeNames().hasMoreElements()) {
                final String[] values = request.getParameterValues("id");
                if (values.length > 0) {
                    id.setData(Integer.parseInt(values[0]));
                }
            } else {
                initializer.accept(this);
            }
        }

        public boolean validate() {
            return false;
        }
    }

    public static class IntegerField {
        private int data;

        public void setData(int data) {
            this.data = data;
        }
    }
}
