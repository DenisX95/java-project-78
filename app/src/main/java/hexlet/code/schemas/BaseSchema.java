package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    public BaseSchema<T>  required() {
        checks.put("required", Objects::nonNull);
        return this;
    }

    public boolean isValid(T value) {
        for (var check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
