package kr.redo.wtforms;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.ImmutableMap;
import kr.redo.wtforms.fields.AbstractField;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.IntStream;

import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

abstract public class Form {
    @NotNull
    private AbstractField[] wtfFields;

    public static <F extends Form> F bind(Class<F> formClass) throws Exception {
        final F form = formClass.newInstance();
        final MethodAccess methodAccess = MethodAccess.get(formClass);
        final String[] methodNames = methodAccess.getMethodNames();
        final Class[][] parameterTypes = methodAccess.getParameterTypes();
        final Class[] returnTypes = methodAccess.getReturnTypes();
        final AbstractField[] fields = IntStream.range(0, methodNames.length)
                .filter(i -> parameterTypes[i].length == 0 && AbstractField.class.isAssignableFrom(returnTypes[i]) && methodNames[i].startsWith("get"))
                .mapToObj(i -> {
                    final AbstractField field = (AbstractField) methodAccess.invoke(form, i);
                    field.bind(form, UPPER_CAMEL.to(LOWER_HYPHEN, methodNames[i].substring(3)));
                    //noinspection unchecked
                    return field;
                }).toArray(AbstractField[]::new);
        form.setWtfFields(fields);
        return form;
    }

    @NotNull
    public AbstractField[] getWtfFields() {
        return wtfFields;
    }

    protected void setWtfFields(@NotNull AbstractField[] wtfFields) {
        this.wtfFields = wtfFields;
    }

    @NotNull
    public String getWtfPrefix() {
        return "wtf-";
    }

    public void wtfProcessData(HttpServletRequest request) {
        for (AbstractField field : getWtfFields()) {
            field.processData(request);
        }
    }

    public ImmutableMap<String, String> getParameterMap() {
        final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        for (AbstractField field : getWtfFields()) {
            final String parameterValue = field.getParameterValue();
            if (parameterValue == null) {
                continue;
            }
            builder.put(field.getParameterName(), parameterValue);
        }
        return builder.build();
    }
}
