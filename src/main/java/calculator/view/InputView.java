package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INPUT_PROMPT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";

    public String readInput() {
        System.out.println(INPUT_PROMPT_MESSAGE);
        String input = Console.readLine();

        if (input == null) {
            throw new IllegalArgumentException("입력값이 null입니다.");
        }

        return input;
    }
}
