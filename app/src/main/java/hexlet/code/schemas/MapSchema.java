package hexlet.code.schemas;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema sizeof(int size) {
        checks.put("size", value -> value.size() == size);
        return this;
    }

    @Override
    public MapSchema required() {
        super.required();
        checks.put("required", value -> !value.isEmpty());
        return this;
    }
}
