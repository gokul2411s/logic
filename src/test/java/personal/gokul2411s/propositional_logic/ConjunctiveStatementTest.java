package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.*;

public class ConjunctiveStatementTest {

    @Test
    public void conjunctiveStatement_shouldPrintCorrectly() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Symbol c = symbol("C");
        Statement s = conjuncted(a, b, c);

        // When
        String sp = s.prettyPrinted();

        // Then
        assertThat(sp, equalTo("(A AND B AND C)"));
    }

    @Test
    public void conjunctiveStatement_shouldReturnSubStatementsInOrder() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Symbol c = symbol("C");
        Statement s = conjuncted(a, b, c);

        // When
        Iterator<Statement> iter = s.iterator();

        // Then
        assertThat(iter.next(), equalTo(a));
        assertThat(iter.next(), equalTo(b));
        assertThat(iter.next(), equalTo(c));
        assertThat(iter.hasNext(), equalTo(false));
    }

    @Test
    public void conjunctiveStatement_shouldBeConjunctive() {
        assertThat(conjuncted(symbol("a"), symbol("b")).isConjunctive(), equalTo(true));
    }

    @Test
    public void conjunctiveStatement_shouldNotBeDisjunctive() {
        assertThat(conjuncted(symbol("a"), symbol("b")).isDisjunctive(), equalTo(false));
    }

    @Test
    public void conjunctiveStatement_inCnf_shouldRecursivelyConvertSubStatementsToCnf() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Symbol c = symbol("C");
        Symbol d = symbol("D");
        Statement n = negated(conjuncted(c, d));
        Statement s = conjuncted(a, b, n);

        // When
        Statement sInCnf = s.inCnf();

        // Then
        assertThat(sInCnf, equalTo(conjuncted(a.inCnf(), b.inCnf(), n.inCnf())));
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
