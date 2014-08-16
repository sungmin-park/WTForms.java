package kr.redo.wtforms;

import com.esotericsoftware.reflectasm.MethodAccess;
import kr.redo.wtforms.fields.Field;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.IntStream;

import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

abstract public class Form {
    @NotNull
    private Field[] wtfFields;

    public static <F extends Form> F bind(Class<F> formClass) throws Exception {
        final F form = formClass.newInstance();
        final MethodAccess methodAccess = MethodAccess.get(formClass);
        final String[] methodNames = methodAccess.getMethodNames();
        final Class[][] parameterTypes = methodAccess.getParameterTypes();
        final Class[] returnTypes = methodAccess.getReturnTypes();
        final Field[] fields = IntStream.range(0, methodNames.length)
                .filter(i -> parameterTypes[i].length == 0 && Field.class.isAssignableFrom(returnTypes[i]) && methodNames[i].startsWith("get"))
                .mapToObj(i -> {
                    final Field field = (Field) methodAccess.invoke(form, i);
                    field.bind(form, UPPER_CAMEL.to(LOWER_HYPHEN, methodNames[i].substring(3)));
                    //noinspection unchecked
                    return field;
                }).toArray(Field[]::new);
        form.setWtfFields(fields);
        return form;
    }

    @NotNull
    public Field[] getWtfFields() {
        return wtfFields;
    }

    protected void setWtfFields(@NotNull Field[] wtfFields) {
        this.wtfFields = wtfFields;
    }

    @NotNull
    public String getWtfPrefix() {
        return "wtf-";
    }

    public void wtfProcessData(HttpServletRequest request) {
        for (Field field : getWtfFields()) {
            field.processData(request);
        }
    }
}
