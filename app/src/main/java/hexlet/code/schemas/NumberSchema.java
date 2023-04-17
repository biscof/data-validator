package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private Integer[] range;
    private boolean positive;
    private boolean rangeDefined;

    @Override
    protected boolean check(Object obj) {
        boolean isValid = obj instanceof Integer
                || obj == null;
        if (positive) {
            isValid = obj instanceof Integer && ((Integer) obj) > 0
                    || obj == null;
        }
        if (required) {
            isValid = obj instanceof Integer;
        }
        if (required && positive) {
            isValid = obj instanceof Integer && ((Integer) obj) > 0;
        }
        if (rangeDefined) {
            isValid = obj instanceof Integer
                    && ((Integer) obj) >= range[0]
                    && ((Integer) obj) <= range[1];
        }
        return isValid;
    }

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(Integer from, Integer to) {
        range = new Integer[2];
        if (from <= to) {
            this.range[0] = from;
            this.range[1] = to;
            rangeDefined = true;
        }
        return this;
    }
}
