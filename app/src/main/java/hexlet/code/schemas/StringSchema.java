package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private String expectedSubstring;
    private int minLength;
    private boolean shouldContainSubstring;
    private boolean hasMinLength;

    @Override
    protected boolean check(Object obj) {
        boolean isValid = isString(obj) || obj == null;

        if (required && isEmpty(obj)) {
            isValid = false;
        }
        if (shouldContainSubstring && !containsSubstring(obj, expectedSubstring)) {
            isValid = false;
        }
        if (hasMinLength && !isLongEnough(obj)) {
            isValid = false;
        }
        return isValid;
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        this.hasMinLength = true;
        return this;
    }

    public StringSchema contains(String str) {
        this.shouldContainSubstring = true;
        this.expectedSubstring = str.toLowerCase();
        return this;
    }

    private boolean isString(Object obj) {
        return obj instanceof String;
    }

    private boolean isEmpty(Object obj) {
        return obj == null || (isString(obj) && ((String) obj).length() == 0);
    }

    private boolean containsSubstring(Object obj, String substr) {
        return isString(obj) && ((String) obj).toLowerCase().contains(substr);
    }

    private boolean isLongEnough(Object obj) {
        return isString(obj) && ((String) obj).length() >= minLength;
    }
}
