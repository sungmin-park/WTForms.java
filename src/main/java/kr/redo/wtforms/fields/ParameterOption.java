package kr.redo.wtforms.fields;

public class ParameterOption {
    private String value;
    private String label;

    public ParameterOption(String value, String label) {

        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
