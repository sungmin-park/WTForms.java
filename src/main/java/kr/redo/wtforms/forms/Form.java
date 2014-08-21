package kr.redo.wtforms.forms;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.base.CaseFormat;
import kr.redo.wtforms.fields.Field;

public class Form {
    public static <F extends Form> F bind(Class<F> formClass) throws IllegalAccessException, InstantiationException {
        final F form = formClass.newInstance();
        final MethodAccess methodAccess = MethodAccess.get(formClass);
        final String[] methodNames = methodAccess.getMethodNames();
        final Class[][] parameterTypes = methodAccess.getParameterTypes();
        final Class[] returnTypes = methodAccess.getReturnTypes();
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
            if (!Field.class.isAssignableFrom(returnTypes[i])) {
                continue;
            }
            final Field field = ((Field) methodAccess.invoke(form, i));
            field.bind(form,  CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, methodName.substring(3)));
        }
        return form;
    }

    public String prefix() {
        return "wtf-";
    }
}
