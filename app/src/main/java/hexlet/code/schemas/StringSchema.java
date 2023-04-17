package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    private String stringToContain;
    private int minLen;
    private boolean contains;
    private boolean minLenSet;

    @Override
    protected boolean check(Object obj) {
        boolean isValid = obj instanceof String
                || obj == null;
        if (required) {
            isValid = obj instanceof String && ((String) obj).length() > 0;
        }
        if (contains) {
            isValid = obj instanceof String && ((String) obj).toLowerCase().contains(stringToContain);
        }
        if (minLenSet) {
            isValid = obj instanceof String && ((String) obj).length() >= minLen;
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
