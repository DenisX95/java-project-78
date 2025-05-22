package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {
    private StringSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().string();
    }

    @Test
    void testIsValidWithoutRequired() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("Test")).isTrue();
    }

    @Test
    void testIsValidWithRequired() {
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("Test")).isTrue();
    }

    @Test
    void testMinLength() {
        schema.minLength(0);

        assertThat(schema.isValid("")).isTrue();

        schema.minLength(5);

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("Test")).isFalse();
        assertThat(schema.isValid("TestTest")).isTrue();
    }

    @Test
    void testContains() {
        schema.contains("");

        assertThat(schema.isValid(null)).isFalse();

        assertThat(schema.isValid("Test")).isTrue();

        schema.contains("est");
        assertThat(schema.isValid("Test")).isTrue();
        assertThat(schema.isValid("Task")).isFalse();

        schema.contains("ask");
        assertThat(schema.isValid("Task")).isTrue();
        assertThat(schema.isValid("Test")).isFalse();
    }

    @Test
    void testMethodChaining() {
        schema.required().minLength(5).contains("st");

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("Test")).isFalse();
        assertThat(schema.isValid("My Test")).isTrue();
    }
}
