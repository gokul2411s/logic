package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.negated;
import static personal.gokul2411s.propositional_logic.StatementFactory.symbol;

public class NegativeStatementTest {

    @Test
    public void negativeStatement_shouldPrintCorrectly() {
        // Given
        Symbol a = symbol("A");
        Statement s = negated(a);

        // When
        String sp = s.prettyPrinted();

        // Then
        assertThat(sp, equalTo("(NOT A)"));
    }

    @Test
    public void negativeStatement_shouldEvaluateTrueToFalse() {
        // Given
        Symbol a = symbol("A");
        Statement s = negated(a);
        Model model = Model.builder().assignment(a, LogicalSymbol.TRUE).build();

        // When
        Statement se = s.evaluatedUnder(model);

        // Then
        assertThat(se, equalTo(LogicalSymbol.FALSE));
    }

    @Test
    public void negativeStatement_shouldEvaluateFalseToTrue() {
        // Given
        Symbol a = symbol("A");
        Statement s = negated(a);
        Model model = Model.builder().assignment(a, LogicalSymbol.FALSE).build();

        // When
        Statement se = s.evaluatedUnder(model);

        // Then
        assertThat(se, equalTo(LogicalSymbol.TRUE));
    }

    @Test
    public void negativeStatement_shouldRemainAsIs_ifNoRelevantModel() {
        // Given
        Symbol a = symbol("A");
        Statement s = negated(a);
        Model model = Model.builder().build();

        // When
        Statement se = s.evaluatedUnder(model);

        // Then
        assertThat(se, equalTo(s));
    }
}
