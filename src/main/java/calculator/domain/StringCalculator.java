package calculator.domain;

public class StringCalculator {

    public int add(String text) {
        ExpressionSplitter splitter = new ExpressionSplitter();
        ExpressionComponents components = splitter.split(text);

        Delimiters delimiters = Delimiters.withDefaults();
        delimiters.add(components.delimiter());

        // TODO: 덧셈 로직
        return 0;
    }
}
