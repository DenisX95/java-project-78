package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().number();
    }

    @Test
    void testRequired() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(0)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(0)).isTrue();
    }

    @Test
    void testPositive() {
        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(-1)).isTrue();

        schema.positive();

        assertThat(schema.isValid(null)).isTrue();

        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(-1)).isFalse();
    }

    @Test
    void testRange() {
        schema.range(1, 5);

        assertThat(schema.isValid(null)).isTrue();

        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(4)).isTrue();
        assertThat(schema.isValid(-1)).isFalse();

        schema.range(2, 6);

        assertThat(schema.isValid(1)).isFalse();
        assertThat(schema.isValid(6)).isTrue();
    }

    @Test
    void testMethodChaining() {
        schema.required().positive().range(1, 2);

        assertThat(schema.isValid(null)).isFalse();

        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(3)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
    }

}
