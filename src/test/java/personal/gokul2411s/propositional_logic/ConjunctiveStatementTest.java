package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.conjuncted;
import static personal.gokul2411s.propositional_logic.StatementFactory.symbol;

public class ConjunctiveStatementTest {

    @Test
    public void conjunctiveStatement_shouldPrintCorrectly() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = conjuncted(a, b);

        // When
        String sp = s.prettyPrinted();

        // Then
        assertThat(sp, equalTo("(A AND B)"));
    }

    @Test
    public void conjunctiveStatement_withTwoSymbols_shouldEvaluateToOneOfThem_whenTheOtherSymbolIsTrue() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = conjuncted(a, b);
        Model model1 = Model.builder().assignment(b, LogicalSymbol.TRUE).build();
        Model model2 = Model.builder().assignment(a, LogicalSymbol.TRUE).build();

        // When
        Statement se1 = s.evaluatedUnder(model1);
        Statement se2 = s.evaluatedUnder(model2);

        // Then
        assertThat(se1, equalTo(a));
        assertThat(se2, equalTo(b));
    }

    @Test
    public void conjunctiveStatement_withTwoSymbols_shouldEvaluateToFalse_whenOneOfTheSymbolsIsFalse() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = conjuncted(a, b);
        Model model1 = Model.builder().assignment(b, LogicalSymbol.FALSE).build();
        Model model2 = Model.builder().assignment(a, LogicalSymbol.FALSE).build();

        // When
        Statement se1 = s.evaluatedUnder(model1);
        Statement se2 = s.evaluatedUnder(model2);

        // Then
        assertThat(se1, equalTo(LogicalSymbol.FALSE));
        assertThat(se2, equalTo(LogicalSymbol.FALSE));
    }

    @Test
    public void conjunctiveStatement_withTwoSymbols_shouldEvaluateToItself_ifNoRelevantModel() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = conjuncted(a, b);
        Model model = Model.builder().build();

        // When
        Statement se = s.evaluatedUnder(model);

        // Then
        assertThat(se, equalTo(s));
    }
}
