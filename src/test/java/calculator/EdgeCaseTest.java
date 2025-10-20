package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EdgeCaseTest extends NsTest {

    @Test
    void 개행_문자를_커스텀_구분자로_사용_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//\\n\\n1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자만_있고_숫자_없음_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//;\\n"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 빈_문자열_입력_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException(""))
                    .isInstanceOf(IllegalArgumentException.class);
        });

    }

    @Test
    void 공백만_있는_입력_예외() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("   "))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 공백을_커스텀_구분자로_사용() {
        assertSimpleTest(() -> {
            run("// \\n1 2 3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 연속된_구분자_처리() {
        assertSimpleTest(() -> {
            run("1,,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 시작이_구분자인_경우() {
        assertSimpleTest(() -> {
            run(",1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 끝이_구분자인_경우() {
        assertSimpleTest(() -> {
            run("1,2,3,");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 시작과_끝이_모두_구분자인_경우() {
        assertSimpleTest(() -> {
            run(",1,2,3:");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 빈_커스텀_구분자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자가_숫자인_경우_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//1\\n112"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자가_여러_자리_숫자인_경우_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//123\\n1123"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 음수_입력_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 소수_입력_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1.5,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자가_아닌_문자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 유니코드_이모지_구분자() {
        assertSimpleTest(() -> {
            run("//😀\\n1😀2😀3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 유니코드_한글_구분자() {
        assertSimpleTest(() -> {
            run("//가\\n1가2가3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 정규식_특수문자_구분자_대괄호() {
        assertSimpleTest(() -> {
            run("//[***]\\n1[***]2[***]3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 정규식_특수문자_구분자_별표() {
        assertSimpleTest(() -> {
            run("//*\\n1*2*3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 정규식_특수문자_구분자_플러스() {
        assertSimpleTest(() -> {
            run("//+\\n1+2+3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 정규식_특수문자_구분자_물음표() {
        assertSimpleTest(() -> {
            run("//?\\n1?2?3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 정규식_특수문자_구분자_점() {
        assertSimpleTest(() -> {
            run("//.\\n1.2.3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 정규식_특수문자_구분자_파이프() {
        assertSimpleTest(() -> {
            run("//|\\n1|2|3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 하이픈을_구분자로_사용() {
        assertSimpleTest(() -> {
            run("//-\\n1-2-3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 큰_숫자_합산() {
        assertSimpleTest(() -> {
            run("1000000,2000000,3000000");
            assertThat(output()).contains("결과 : 6000000");
        });
    }

    @Test
    void 매우_긴_구분자() {
        assertSimpleTest(() -> {
            run("//[***SEPARATOR***]\\n1[***SEPARATOR***]2[***SEPARATOR***]3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 숫자_하나만_입력() {
        assertSimpleTest(() -> {
            run("100");
            assertThat(output()).contains("결과 : 100");
        });
    }

    @Test
    void 기본_구분자와_커스텀_구분자_혼합() {
        assertSimpleTest(() -> {
            run("//;\\n1,2;3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 개행_문자가_누락된_경우_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 탭을_커스텀_구분자로_사용() {
        assertSimpleTest(() -> {
            run("//\\t\\n1\\t2\\t3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
