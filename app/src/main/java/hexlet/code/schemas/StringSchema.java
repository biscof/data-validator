package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String stringToContain;
    private int minLen;
    private boolean contains;
    private boolean minLenSet;

    @Override
    protected boolean check(Object obj) {
        boolean isValid = obj instanceof String || obj == null;

        if (required
                && !(obj instanceof String && ((String) obj).length() > 0)) {
            isValid = false;
        }
        if (contains
                && !(obj instanceof String && ((String) obj).toLowerCase().contains(stringToContain))) {
            isValid = false;
        }
        if (minLenSet
                && !(obj instanceof String && ((String) obj).length() >= minLen)) {
            isValid = false;
        }
        return isValid;
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLen = length;
        this.minLenSet = true;
        return this;
    }

    public StringSchema contains(String str) {
        this.contains = true;
        this.stringToContain = str.toLowerCase();
        return this;
    }
}
