package calculator.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Delimiters {
    private static final Delimiter DEFAULT_COMMA = new Delimiter(",");
    private static final Delimiter DEFAULT_COLON = new Delimiter(":");

    private final Set<Delimiter> values;

    private Delimiters(Set<Delimiter> values) {
        this.values = values;
    }

    public static Delimiters withDefaults() {
        return new Delimiters(new HashSet<>(Set.of(DEFAULT_COMMA, DEFAULT_COLON)));
    }

    public void add(Delimiter customDelimiter) {
        if (customDelimiter != null) {
            values.add(customDelimiter);
        }
    }

    public String toRegexPattern() {
        return values.stream()
                .map(Delimiter::value)
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }
}
