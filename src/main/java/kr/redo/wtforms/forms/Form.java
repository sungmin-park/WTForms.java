package kr.redo.wtforms.forms;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.base.CaseFormat;
import kr.redo.wtforms.fields.AbstractField;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Form {
    private List<AbstractField> wtfFields = new ArrayList<>();

    public static <F extends Form> F bind(Class<F> formClass) throws IllegalAccessException, InstantiationException {
        final F form = formClass.newInstance();
        final MethodAccess methodAccess = MethodAccess.get(formClass);
        final String[] methodNames = methodAccess.getMethodNames();
        final Class[][] parameterTypes = methodAccess.getParameterTypes();
        final Class[] returnTypes = methodAccess.getReturnTypes();
        final List<AbstractField> wtfFields = form.getWtfFields();
        for (int i = 0; i < methodNames.length; i++) {
            // check is this a simple getter
            if (parameterTypes[i].length > 0) {
                continue;
            }
            // check getter from method name
            final String methodName = methodNames[i];
            if (!methodName.startsWith("get")) {
                continue;
            }
            // check return type is field
            if (!AbstractField.class.isAssignableFrom(returnTypes[i])) {
                continue;
            }
            final AbstractField abstractField = ((AbstractField) methodAccess.invoke(form, i));
            abstractField.bind(form, CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, methodName.substring(3)));
            wtfFields.add(abstractField);
        }
        return form;
    }

    public String prefix() {
        return "wtf-";
    }

    public void processData(HttpServletRequest request) {
        for (AbstractField field : getWtfFields()) {
            field.processData(request);
        }
    }

    public boolean validate() throws Exception {
        for (AbstractField field : wtfFields) {
            field.validate();
        }
        return !hasErrors();
    }

    public boolean hasErrors() {
        return wtfFields.stream().filter(AbstractField::hasErrors).findAny().isPresent();
    }

    public Map<String, List<String>> getErrors() {
        return wtfFields.stream()
                .filter(AbstractField::hasErrors)
                .collect(Collectors.toMap(AbstractField::getParameterName, AbstractField::getErrors));
    }

    public List<AbstractField> getWtfFields() {
        return wtfFields;
    }

    public void clearErrors() {
        getWtfFields().forEach(AbstractField::clearErrors);
    }
}
