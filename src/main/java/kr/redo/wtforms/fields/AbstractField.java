package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;

import javax.servlet.http.HttpServletRequest;

abstract public class AbstractField {
    String name;
    private Form form;

    public String getName() {
        return name;
    }

    public abstract void processData(HttpServletRequest request);

    public String getParameterName() {
        return form.prefix() + name;
    }

    public void bind(Form form, String name) {
        this.form = form;
        this.name = name;
    }
}
