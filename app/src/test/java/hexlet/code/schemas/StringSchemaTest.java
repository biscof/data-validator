package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    @Test
    public void stringSchemaTest() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(2));

        schema.required();
        assertTrue(schema.isValid("John Smith is a teacher"));
        assertTrue(schema.isValid("Smith"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(2));
        assertFalse(schema.isValid(""));

        boolean result;
        result = schema.contains("John").isValid("John Smith is a teacher");
        assertTrue(result);
        result = schema.contains("john").isValid("John Smith is a teacher");
        assertTrue(result);
        result = schema.contains("ith").isValid("John Smith is a teacher");
        assertTrue(result);
        result = schema.contains("abc").isValid("John Smith is a teacher");
        assertFalse(result);

        assertFalse(schema.isValid("John Smith is a teacher"));

        result = schema.minLength(2).isValid("he");
        assertTrue(result);
        result = schema.minLength(2).isValid("hello");
        assertTrue(result);
        result = schema.minLength(2).isValid("h");
        assertFalse(result);

        assertTrue(schema.isValid("John Smith is a teacher"));
    }
}
