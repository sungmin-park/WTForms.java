package kr.redo.wtforms.fields;

import kr.redo.wtforms.forms.Form;
import kr.redo.wtforms.transformers.Transformer;

import javax.servlet.http.HttpServletRequest;

abstract public class AbstractField<T> {
    private String name;
    private Form form;
    private Transformer<T> transformer;

    public Transformer<T> getTransformer() {
        return transformer;
    }

    public void setTransformer(Transformer<T> transformer) {
        this.transformer = transformer;
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
}
