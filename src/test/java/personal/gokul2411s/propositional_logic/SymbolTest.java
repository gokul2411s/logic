package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.symbol;

public class SymbolTest {

    @Test
    public void symbol_shouldPrintAsIs() {
        assertThat(symbol("A").prettyPrinted(), equalTo("A"));
    }

    @Test
    public void symbol_shouldEvaluateToLogicalSymbol_whenModelSpecifiesAValueForIt() {
        // Given
        Symbol a = symbol("A");
        Model model = Model.builder().assignment(a, LogicalSymbol.TRUE).build();

        // When
        Statement ae = a.evaluatedUnder(model);

        // Then
        assertThat(ae, equalTo(LogicalSymbol.TRUE));
    }

    @Test
    public void symbol_shouldEvaluateToItself_whenModelDoesNotSpecifyAValueForIt() {
        // Given
        Symbol a = symbol("A");
        Model model = Model.builder().assignment(symbol("B"), LogicalSymbol.TRUE).build();

        // When
        Statement ae = a.evaluatedUnder(model);

        // Then
        assertThat(ae, equalTo(a));
    }
}
