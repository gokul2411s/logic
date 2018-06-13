package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.disjuncted;
import static personal.gokul2411s.propositional_logic.StatementFactory.symbol;

public class DisjunctiveStatementTest {

    @Test
    public void disjunctiveStatement_shouldPrintCorrectly() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Symbol c = symbol("C");
        Statement s = disjuncted(a, b, c);

        // When
        String sp = s.prettyPrinted();

        // Then
        assertThat(sp, equalTo("(A OR B OR C)"));
    }

    @Test
    public void disjunctiveStatement_withTwoSymbols_shouldEvaluateToTrue_whenEitherOfTheSymbolsIsTrue() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = disjuncted(a, b);
        Model model1 = Model.builder().assignment(b, LogicalSymbol.TRUE).build();
        Model model2 = Model.builder().assignment(a, LogicalSymbol.TRUE).build();

        // When
        Statement se1 = s.evaluatedUnder(model1);
        Statement se2 = s.evaluatedUnder(model2);

        // Then
        assertThat(se1, equalTo(LogicalSymbol.TRUE));
        assertThat(se2, equalTo(LogicalSymbol.TRUE));
    }

    @Test
    public void disjunctiveStatement_withTwoSymbols_shouldEvaluateToOneOfThem_whenTheOtherSymbolIsFalse() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = disjuncted(a, b);
        Model model1 = Model.builder().assignment(b, LogicalSymbol.FALSE).build();
        Model model2 = Model.builder().assignment(a, LogicalSymbol.FALSE).build();

        // When
        Statement se1 = s.evaluatedUnder(model1);
        Statement se2 = s.evaluatedUnder(model2);

        // Then
        assertThat(se1, equalTo(a));
        assertThat(se2, equalTo(b));
    }

    @Test
    public void disjunctiveStatement_withTwoSymbols_shouldEvaluateToItself_ifNoRelevantModel() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = disjuncted(a, b);
        Model model = Model.builder().build();

        // When
        Statement se = s.evaluatedUnder(model);

        // Then
        assertThat(se, equalTo(s));
    }
}
