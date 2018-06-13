package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.*;

public class ImplicationTest {

    @Test
    public void a_implies_b_shouldBehaveTheSameAs_not_a_disjunctedWith_b() {

        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s1 = implication(a, b);
        Statement s2 = disjuncted(negated(a), b);

        Model model_a_b_true =
                Model.builder()
                        .assignment(a, LogicalSymbol.TRUE)
                        .assignment(b, LogicalSymbol.TRUE)
                        .build();

        Model model_a_true_b_false =
                Model.builder()
                        .assignment(a, LogicalSymbol.TRUE)
                        .assignment(b, LogicalSymbol.FALSE)
                        .build();

        Model model_a_false_b_true =
                Model.builder()
                        .assignment(a, LogicalSymbol.FALSE)
                        .assignment(b, LogicalSymbol.TRUE)
                        .build();

        Model model_a_b_false =
                Model.builder()
                        .assignment(a, LogicalSymbol.FALSE)
                        .assignment(b, LogicalSymbol.FALSE)
                        .build();

        assertThat(s1.evaluatedUnder(model_a_b_true), equalTo(s2.evaluatedUnder(model_a_b_true)));
        assertThat(s1.evaluatedUnder(model_a_true_b_false), equalTo(s2.evaluatedUnder(model_a_true_b_false)));
        assertThat(s1.evaluatedUnder(model_a_false_b_true), equalTo(s2.evaluatedUnder(model_a_false_b_true)));
        assertThat(s1.evaluatedUnder(model_a_b_false), equalTo(s2.evaluatedUnder(model_a_b_false)));
    }


    @Test
    public void a_implies_b_and_b_implies_a_shouldBehaveTheSameAs_a_implies_b_conjunctedWith_b_implies_a() {

        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s1 = twoWayImplication(a, b);
        Statement s2 = conjuncted(implication(a, b), implication(b, a));

        Model model_a_b_true =
                Model.builder()
                        .assignment(a, LogicalSymbol.TRUE)
                        .assignment(b, LogicalSymbol.TRUE)
                        .build();

        Model model_a_true_b_false =
                Model.builder()
                        .assignment(a, LogicalSymbol.TRUE)
                        .assignment(b, LogicalSymbol.FALSE)
                        .build();

        Model model_a_false_b_true =
                Model.builder()
                        .assignment(a, LogicalSymbol.FALSE)
                        .assignment(b, LogicalSymbol.TRUE)
                        .build();

        Model model_a_b_false =
                Model.builder()
                        .assignment(a, LogicalSymbol.FALSE)
                        .assignment(b, LogicalSymbol.FALSE)
                        .build();

        assertThat(s1.evaluatedUnder(model_a_b_true), equalTo(s2.evaluatedUnder(model_a_b_true)));
        assertThat(s1.evaluatedUnder(model_a_true_b_false), equalTo(s2.evaluatedUnder(model_a_true_b_false)));
        assertThat(s1.evaluatedUnder(model_a_false_b_true), equalTo(s2.evaluatedUnder(model_a_false_b_true)));
        assertThat(s1.evaluatedUnder(model_a_b_false), equalTo(s2.evaluatedUnder(model_a_b_false)));
    }
}
