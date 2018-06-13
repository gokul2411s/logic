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
    public void logicalSymbol_shouldContainNoSubStatements() {
        assertThat(LogicalSymbol.TRUE.iterator().hasNext(), equalTo(false));
        assertThat(LogicalSymbol.FALSE.iterator().hasNext(), equalTo(false));
    }

    @Test
    public void logicalSymbol_shouldBeNeitherConjunctiveNorDisjunctive() {
        assertThat(LogicalSymbol.TRUE.isConjunctive(), equalTo(false));
        assertThat(LogicalSymbol.TRUE.isDisjunctive(), equalTo(false));
        assertThat(LogicalSymbol.FALSE.isConjunctive(), equalTo(false));
        assertThat(LogicalSymbol.FALSE.isDisjunctive(), equalTo(false));
    }

    @Test
    public void logicalSymbol_inCnf_shouldBeTheSame() {
        assertThat(LogicalSymbol.TRUE.inCnf(), equalTo(LogicalSymbol.TRUE));
        assertThat(LogicalSymbol.FALSE.inCnf(), equalTo(LogicalSymbol.FALSE));
    }

    @Test
    public void logicalSymbol_shouldReturnItselfUnderEvaluation() {
        Model model = Model.builder().build();
        assertThat(LogicalSymbol.TRUE.evaluatedUnder(model), equalTo(LogicalSymbol.TRUE));
        assertThat(LogicalSymbol.FALSE.evaluatedUnder(model), equalTo(LogicalSymbol.FALSE));

    }
}
