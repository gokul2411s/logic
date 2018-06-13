package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.*;

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
    public void negativeStatement_shouldReturnTheOneSubStatement() {
        // Given
        Symbol a = symbol("A");
        Statement s = negated(a);

        // When
        Iterator<Statement> iter = s.iterator();

        // Then
        assertThat(iter.next(), equalTo(a));
        assertThat(iter.hasNext(), equalTo(false));
    }

    @Test
    public void negativeStatement_shouldBeNeitherConjunctiveNorDisjunctive() {
        Statement s = negated(symbol("A"));
        assertThat(s.isConjunctive(), equalTo(false));
        assertThat(s.isDisjunctive(), equalTo(false));
    }

    @Test
    public void negativeStatement_inCnf_shouldHandleConjunctionsCorrectly() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = negated(conjuncted(a, b));

        // When
        Statement sInCnf = s.inCnf();

        // Then
        assertThat(sInCnf, equalTo(disjuncted(negated(a), negated(b))));
    }

    @Test
    public void negativeStatement_inCnf_shouldHandleDisjunctionsCorrectly() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Statement s = negated(disjuncted(a, b));

        // When
        Statement sInCnf = s.inCnf();

        // Then
        assertThat(sInCnf, equalTo(conjuncted(negated(a), negated(b))));
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
