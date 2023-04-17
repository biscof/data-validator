package hexlet.code.schemas;

public abstract class BaseSchema {
    protected boolean required;

    public final boolean isValid(Object obj) {
        return check(obj);
    }

    protected abstract boolean check(Object obj);
}
