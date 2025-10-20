package calculator.domain;

import java.util.Objects;

public class ExpressionSplitter {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String NEWLINE_ACTUAL = "\n";
    private static final String NEWLINE_LITERAL = "\\n";

    public ExpressionComponents split(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            throw new IllegalArgumentException("식을 입력해주세요.");
        }

        String normalizedInput = input.replace(NEWLINE_LITERAL, NEWLINE_ACTUAL);

        if (normalizedInput.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            return splitWithCustomDelimiter(normalizedInput);
        }
        return splitWithDefaultDelimiter(normalizedInput);
    }

    private ExpressionComponents splitWithCustomDelimiter(String input) {
        validateCustomDelimiterSyntax(input);

        int suffixIndex = input.indexOf(NEWLINE_ACTUAL);
        String rawDelimiter = input.substring(CUSTOM_DELIMITER_PREFIX.length(), suffixIndex);
        String numberPart = input.substring(suffixIndex + NEWLINE_ACTUAL.length());

        validateNumberPart(numberPart);

        Delimiter delimiter = new Delimiter(rawDelimiter);
        return new ExpressionComponents(delimiter, numberPart);
    }

    private void validateCustomDelimiterSyntax(String input) {
        if (!input.contains(NEWLINE_ACTUAL)) {
            throw new IllegalArgumentException("커스텀 구분자 정의의 끝을 나타내는 '\\n' 문자가 누락되었습니다.");
        }
    }

    private ExpressionComponents splitWithDefaultDelimiter(String input) {
        validateNumberPart(input);
        return new ExpressionComponents(null, input);
    }

    private void validateNumberPart(String numberPart) {
        if (numberPart == null || numberPart.isEmpty()) {
            throw new IllegalArgumentException("숫자 부분이 비어있을 수 없습니다.");
        }
        if (!Character.isDigit(numberPart.charAt(0))) {
            throw new IllegalArgumentException("숫자 부분은 숫자로 시작해야 합니다.");
        }
        if (!Character.isDigit(numberPart.charAt(numberPart.length() - 1))) {
            throw new IllegalArgumentException("숫자 부분은 숫자로 끝나야 합니다.");
        }
    }
}
