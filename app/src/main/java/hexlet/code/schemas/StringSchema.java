package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    public StringSchema minLength(int minLengthSize) {
        checks.put("minLength", value -> value.length() >= minLengthSize);
        return this;
    }

    public StringSchema contains(String containsText) {
        checks.put("contains", value -> value.contains(containsText));
        return this;
    }

    @Override
    public StringSchema required() {
        super.required();
        checks.put("required", value -> value != null && !value.isEmpty());
        return this;
    }
}
