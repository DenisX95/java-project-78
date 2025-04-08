package hexlet.code.schemas;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringSchema {
    private boolean isRequired = false;
    private int minLengthSize = 0;
    private String containsText = "";

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int minLengthSize) {
        this.minLengthSize = minLengthSize;
        return this;
    }

    public StringSchema contains(String containsText) {
        this.containsText = containsText;
        return this;
    }

    public boolean isValid(String text) {
        if (text == null) {
            return !isRequired;
        }

        if (isRequired && text.isEmpty()) {
            return false;
        }

        if (text.length() < minLengthSize) {
            return false;
        }

        if (!text.contains(containsText)) {
            return false;
        }

        return true;
    }
}
