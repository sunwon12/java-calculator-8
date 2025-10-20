
### 🌟 전체 메시지 흐름 

1.  **`CalculatorApplication`**: 애플리케이션의 시작과 끝, 그리고 객체 간의 흐름을 총괄합니다.
2.  **`CalculatorApplication` → `InputView`**: `readInput()` 메시지를 보내 사용자 입력을 요청합니다.
3.  **`InputView` → 사용자**: 화면에 `"덧셈할 문자열을 입력해 주세요."`를 출력합니다.
4.  **사용자 → `InputView`**: 예를 들어, `"//[***]\n1[***]2,3"`을 입력합니다.
5.  **`InputView` → `CalculatorApplication`**: 입력받은 원본 문자열(`rawInput`) `"//[***]\n1[***]2,3"`을 반환합니다.
6.  **`CalculatorApplication` → `StringCalculator`**: `add("//[***]\n1[***]2,3")` 메시지를 보내 계산을 지시합니다.
7.  **`StringCalculator` → `ExpressionSplitter`**: `split("//[***]\n1[***]2,3")` 메시지를 보내 문자열 분리를 요청합니다.
8.  **`ExpressionSplitter` → `StringCalculator`**: `ExpressionComponents` 객체를 반환합니다. (내용: `delimiterPart` = `"//[***]\n"`, `numberPart` = `"1[***]2,3"`)
9.  **`StringCalculator`**: 반환된 `delimiterPart`를 사용하여 `Delimiter` 객체를 생성합니다.
    `Delimiter delimiter = new Delimiter("//[***]\n");`
    * `Delimiter` 객체는 내부적으로 `[***]`를 자신의 커스텀 구분자 상태로 저장합니다.
10. **`StringCalculator`**: `delimiter` 객체로부터 커스텀 구분자 문자열을 가져옵니다.
11. **`StringCalculator`**: 가져온 커스텀 구분자(`[***]`)를 `StringParser`의 생성자에 주입하여 `StringParser` 인스턴스를 생성합니다.
    `StringParser parser = new StringParser("[***]");`
    * **`StringParser` 생성자 내부**:
        * 기본 구분자 `(`,` , `:`)를 자신의 구분자 목록 상태에 먼저 추가합니다.
        * 전달받은 커스텀 구분자 `[***]`를 구분자 목록 상태에 **추가**합니다.
        * 이제 `parser` 객체는 `(`,` , `:` , `[***]`) 세 가지를 모두 아는 상태가 됩니다.
12. **`StringCalculator` → `parser` (생성된 인스턴스)**: `parse("1[***]2,3")` 메시지를 보내 파싱을 요청합니다.
13. **`parser` (내부)**: 자신이 상태로 가진 모든 구분자 목록 `(`,` , `:` , `[***]`)을 조합하여 최종 정규식 패턴을 만듭니다.
14. **`parser` → `StringCalculator`**: 파싱 및 변환이 완료된 `List<Number>` `[1, 2, 3]`을 반환합니다.
15. **`StringCalculator` → `NumberCalculator`**: `sum(List [1, 2, 3])` 메시지를 보내 덧셈을 요청합니다.
16. **`NumberCalculator` → `StringCalculator`**: 리스트의 모든 숫자를 더한 결과값 `6`을 반환합니다.
17. **`StringCalculator` → `CalculatorApplication`**: 최종 계산 결과 `6`을 반환합니다.
18. **`CalculatorApplication` → `OutputView`**: `printResult(6)` 메시지를 보내 출력을 요청합니다.
19. **`OutputView` → 사용자**: 화면에 최종 결과 `"결과 : 6"`을 출력합니다.
