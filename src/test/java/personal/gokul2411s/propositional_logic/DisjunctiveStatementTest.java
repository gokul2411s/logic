package personal.gokul2411s.propositional_logic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static personal.gokul2411s.propositional_logic.StatementFactory.*;

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
    public void disjunctiveStatement_shouldReturnSubStatementsInOrder() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Symbol c = symbol("C");
        Statement s = disjuncted(a, b, c);

        // When
        Iterator<Statement> iter = s.iterator();

        // Then
        assertThat(iter.next(), equalTo(a));
        assertThat(iter.next(), equalTo(b));
        assertThat(iter.next(), equalTo(c));
        assertThat(iter.hasNext(), equalTo(false));
    }

    @Test
    public void disjunctiveStatement_shouldBeDisjunctive() {
        assertThat(disjuncted(symbol("a"), symbol("b")).isDisjunctive(), equalTo(true));
    }

    @Test
    public void disjunctiveStatement_shouldNotBeConjunctive() {
        assertThat(disjuncted(symbol("a"), symbol("b")).isConjunctive(), equalTo(false));
    }

    @Test
    public void disjunctiveStatement_inCnf_shouldRecursivelyConvertSubStatementsToCnf() {
        // Given
        Symbol a = symbol("A");
        Symbol b = symbol("B");
        Symbol c = symbol("C");
        Symbol d = symbol("D");
        Statement n = negated(disjuncted(c, d));
        Statement s = disjuncted(a, b, n);

        // When
        Statement sInCnf = s.inCnf();

        // Then
        assertThat(sInCnf, equalTo(disjuncted(a.inCnf(), b.inCnf(), n.inCnf())));
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
