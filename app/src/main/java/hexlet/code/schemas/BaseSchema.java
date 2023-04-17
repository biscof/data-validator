package hexlet.code.schemas;

public abstract class BaseSchema {
    protected boolean required;

    public boolean isValid(Object obj) {
        return check(obj);
    }

    protected abstract boolean check(Object obj);
}
