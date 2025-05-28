package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    public final StringSchema minLength(int minLengthSize) {
        checks.put("minLength", value -> value.length() >= minLengthSize);
        return this;
    }

    public final StringSchema contains(String containsText) {
        checks.put("contains", value -> value.contains(containsText));
        return this;
    }

    @Override
    public final StringSchema required() {
        super.required();
        return this;
    }
}
