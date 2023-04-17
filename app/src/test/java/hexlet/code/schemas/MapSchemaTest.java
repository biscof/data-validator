package hexlet.code.schemas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

class MapSchemaTest {
    @Test
    public void mapSchemaTest() {
        MapSchema schema = new MapSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertFalse(schema.isValid("hello"));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void shapeTest() {
        MapSchema schema = new MapSchema();
        StringSchema strSchema = new StringSchema();
        NumberSchema numSchema = new NumberSchema();
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", strSchema.required());
        schemas.put("age", numSchema.positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "John");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Kate");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "James");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));
    }
}
