package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import kr.redo.wtforms.transformers.Transformer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

abstract public class AbstractField<T> {
    private String name;
    private Form form;
    private final List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

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

    public abstract String render() throws Exception;

    public void addError(String message) {
        getErrors().add(message);
    }

    abstract public void validate() throws Exception;

    public boolean hasErrors() {
        return errors.size() > 0;
    }
}
