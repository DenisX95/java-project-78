package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {
    private MapSchema schema;
    private HashMap<String, Integer> map;

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
        assertThat(schema.isValid(new HashMap<>())).isFalse();
    }

    @Test
    void testSizeOf() {
        map.put("1", 1);
        schema.sizeof(2);

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
}
