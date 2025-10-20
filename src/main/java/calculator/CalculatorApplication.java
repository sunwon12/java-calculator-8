package calculator;

import calculator.view.InputView;

public class CalculatorApplication {
    private final InputView inputView;

    public CalculatorApplication() {
        this.inputView = new InputView();
    }

    public void run() {
        inputView.printInputPrompt();
    }
}
