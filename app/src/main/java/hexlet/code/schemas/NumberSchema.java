package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema positive() {
        checks.put("positive", value -> value > 0);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        checks.put("range", value -> value >= min && value <= max);
        return this;
    }

    @Override
    public final NumberSchema required() {
        super.required();
        return this;
    }
}
