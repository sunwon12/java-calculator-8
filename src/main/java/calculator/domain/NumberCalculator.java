package calculator.domain;

import java.util.List;

public class NumberCalculator {

    public long sum(List<Number> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        return numbers.stream()
                .mapToLong(Number::value)
                .sum();
    }
}
