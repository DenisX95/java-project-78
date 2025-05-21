package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean isRequired = false;

    public BaseSchema<T>  required() {
        this.isRequired = true;
        checks.put("required", value -> value != null);
        return this;
    }

    public boolean isValid(T value) {
        if (value == null) {
            return !isRequired;
        }
        for (var check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }
}
