package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected final Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    /**
     * Override this method to implement custom validity logic.
     * @return the current schema with the required check added
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    public final boolean isValid(T value) {
        if (value == null || "".equals(value)) {
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
