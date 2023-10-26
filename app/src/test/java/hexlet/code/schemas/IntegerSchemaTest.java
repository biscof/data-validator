package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    @Test
    public void numberSchemaTest() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(2));
        assertTrue(schema.isValid(-2));
        assertFalse(schema.isValid("5"));

        boolean result;
        result = schema.positive().isValid(null);
        assertTrue(result);
        result = schema.positive().isValid(0);
        assertFalse(result);
        result = schema.positive().isValid(-1);
        assertFalse(result);

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(1));
        assertFalse(schema.isValid("5"));
        assertFalse(schema.isValid(-2));
        assertFalse(schema.isValid(0));

        schema.range(-1, 2);
        assertTrue(schema.isValid(1));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-2));
        assertFalse(schema.isValid(3));
    }
}
