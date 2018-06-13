package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogicalSymbolTest {

    @Test
    public void logicalSymbol_shouldPrintAsIs() {
        assertThat(LogicalSymbol.TRUE.prettyPrinted(), equalTo("true"));
        assertThat(LogicalSymbol.FALSE.prettyPrinted(), equalTo("false"));
    }

    @Test
    public void logicalSymbol_shouldReturnItselfUnderEvaluation() {
        Model model = Model.builder().build();
        assertThat(LogicalSymbol.TRUE.evaluatedUnder(model), equalTo(LogicalSymbol.TRUE));
        assertThat(LogicalSymbol.FALSE.evaluatedUnder(model), equalTo(LogicalSymbol.FALSE));

    }
}
