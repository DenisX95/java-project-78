package hexlet.code.schemas;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema sizeof(int size) {
        checks.put("size", value -> value != null && value.size() == size);
        return this;
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<Map<?, ?>> schemasRule = value -> value == null || schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema<T> subSchema = entry.getValue();

                    if (!value.containsKey(key)) {
                        return true;
                    }

                    T fieldValue = (T) value.get(key);

                    return subSchema.isValid(fieldValue);
                });

        checks.put("shapes", schemasRule);
        return this;
    }
}
