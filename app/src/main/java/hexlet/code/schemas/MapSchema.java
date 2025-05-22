package hexlet.code.schemas;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema sizeof(int size) {
        checks.put("size", value -> value.size() == size);
        return this;
    }

    @Override
    public MapSchema required() {
        super.required();
        checks.put("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<Map<?, ?>> schemasRule = value -> value != null && schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<T> subSchema = entry.getValue();
                    T fieldValue = (T) value.get(key);

                    return subSchema.isValid(fieldValue);
                });

        checks.put("shapes", schemasRule);
        return this;
    }
}
