package hexlet.code.schemas;

public final class IntegerSchema extends BaseSchema {
    private Integer[] range;
    private boolean positive;
    private boolean rangeDefined;

    @Override
    protected boolean check(Object obj) {
        boolean isValid = obj == null || isInteger(obj);

        if (positive && !isPositive(obj)) {
            isValid = false;
        }
        if (required && !isInteger(obj)) {
            isValid = false;
        }
        if (required && positive && !isInteger(obj) && !isPositive(obj)) {
            isValid = false;
        }
        if (rangeDefined && !isInRange(obj, range)) {
            isValid = false;
        }
        return isValid;
    }

    public IntegerSchema required() {
        this.required = true;
        return this;
    }

    public IntegerSchema positive() {
        positive = true;
        return this;
    }

    public IntegerSchema range(Integer from, Integer to) {
        range = new Integer[2];
        if (from <= to) {
            this.range[0] = from;
            this.range[1] = to;
            rangeDefined = true;
        }
        return this;
    }

    private boolean isInteger(Object obj) {
        return obj instanceof Integer;
    }

    private boolean isPositive(Object obj) {
        // If the validated object is null, and it isn't required to hold any specific value,
        // the number will be considered positive.
        return isInteger(obj) && (Integer) obj > 0 || obj == null;
    }

    private boolean isInRange(Object obj, Integer[] range) {
        return isInteger(obj) && (Integer) obj >= range[0] && (Integer) obj <= range[1];
    }
}
