package calculator.domain;

import java.util.Arrays;
import java.util.List;

public class StringParser {
    private final Delimiters delimiters;

    public StringParser(Delimiters delimiters) {
        this.delimiters = delimiters;
    }

    public List<Number> parse(String numberPart) {
        if (numberPart == null || numberPart.isEmpty()) {
            return List.of();
        }

        String regexPattern = delimiters.toRegexPattern();
        return Arrays.stream(numberPart.split(regexPattern))
                .filter(token -> !token.isEmpty())
                .map(this::parseNumber)
                .toList();
    }

    private Number parseNumber(String token) {
        try {
            long value = Long.parseLong(token);
            validatePositiveNumber(value, token);
            return new Number(value);
        } catch (NumberFormatException e) {
            if (token.contains(".")) {
                throw new IllegalArgumentException("소수는 허용되지 않습니다: " + token);
            }
            throw new IllegalArgumentException("숫자로 변환할 수 없습니다: " + token);
        }
    }

    private void validatePositiveNumber(long number, String token) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + token);
        }
    }
}
