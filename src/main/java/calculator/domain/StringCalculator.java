package calculator.domain;

import java.util.List;

public class StringCalculator {

    public int add(String text) {
        ExpressionSplitter splitter = new ExpressionSplitter();
        ExpressionComponents components = splitter.split(text);

        Delimiters delimiters = Delimiters.withDefaults();
        delimiters.add(components.delimiter());

        StringParser parser = new StringParser(delimiters);
        List<Number> numbers = parser.parse(components.numberPart());
        // TODO: 덧셈 로직
        return 0;
    }
}
