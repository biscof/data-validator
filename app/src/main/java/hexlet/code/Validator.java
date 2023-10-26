package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.IntegerSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public IntegerSchema number() {
        return new IntegerSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }
}
