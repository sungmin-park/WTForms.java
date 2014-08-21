package kr.redo.wtforms.fields;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;

public class OptionsField extends AbstractField {
    @Override
    public void processData(HttpServletRequest request) {
        throw new NotImplementedException();
    }
}
