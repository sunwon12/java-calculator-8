package calculator.domain;

public record Delimiter(String value) {
    public Delimiter {
        validate(value);
    }

    private void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자는 비어있을 수 없습니다.");
        }
        if (value.contains("\n")) {
            throw new IllegalArgumentException("커스텀 구분자에 개행 문자를 포함할 수 없습니다.");
        }
        if (isNumeric(value)) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자로만 구성될 수 없습니다.");
        }
    }

    private boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
