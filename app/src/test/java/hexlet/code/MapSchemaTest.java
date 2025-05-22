package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {
    private MapSchema schema;
    private Map<String, Integer> map;

    @BeforeEach
    void setUp() {
        schema = new Validator().map();
        map = new HashMap<String, Integer>();
    }

    @Test
    void testRequired() {
        map.put("1", 1);

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        assertThat(schema.isValid(map)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        assertThat(schema.isValid(map)).isTrue();
    }

    @Test
    void testSizeOf() {
        map.put("1", 1);
        schema.sizeof(2);

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isFalse();

        assertThat(schema.isValid(map)).isFalse();

        map.put("2", 2);
        assertThat(schema.isValid(map)).isTrue();

        schema.sizeof(1);
        assertThat(schema.isValid(map)).isFalse();
    }

    @Test
    void testMethodChaining() {
        map.put("1", 1);

        schema.required().sizeof(1);

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        assertThat(schema.isValid(map)).isTrue();
    }

    @Test
    void testShapeWithSameSchemas() {
        var v = new Validator();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertThat(schema.isValid(human2)).isFalse();

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertThat(schema.isValid(human3)).isFalse();
    }

    @Test
    void testShapeWithNullFieldWithoutRequired() {
        var v = new Validator();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string());
        schemas.put("lastName", v.string().minLength(2));

        schema.shape(schemas);

        Map<String, String> person = new HashMap<>();
        person.put("firstName", null);
        person.put("lastName", "Lee");

        assertThat(schema.isValid(person)).isTrue();
    }

    @Test
    void testShapeWithMissingKey() {
        var v = new Validator();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("city", v.string().required());

        schema.shape(schemas);

        Map<String, String> person = new HashMap<>();
        person.put("name", "Denis");

        assertThat(schema.isValid(person)).isFalse();
    }

}
