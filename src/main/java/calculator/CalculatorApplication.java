package calculator;

import calculator.domain.StringCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorApplication {
    private final InputView inputView;
    private final OutputView outputView;
    private final StringCalculator stringCalculator;

    public CalculatorApplication() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.stringCalculator = new StringCalculator();
    }

    public void run() {
        String rawInput = inputView.readInput();
        long result = stringCalculator.add(rawInput);
        outputView.printResult(result);
    }
}
