package calculator.domain;

public class ExpressionComponents {
    private final String delimiterPart;
    private final String numberPart;

    public ExpressionComponents(String delimiterPart, String numberPart) {
        this.delimiterPart = delimiterPart;
        this.numberPart = numberPart;
    }

    public String getDelimiterPart() {
        return delimiterPart;
    }

    public String getNumberPart() {
        return numberPart;
    }
}
