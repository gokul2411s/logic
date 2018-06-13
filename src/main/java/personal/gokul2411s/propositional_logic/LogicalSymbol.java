package personal.gokul2411s.propositional_logic;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Collections;
import java.util.Iterator;

/**
 * Represents a logical symbol, corresponding to either true or false.
 *
 * <p>This class allows treating logical values as first-class statements.</p>
 */
@AllArgsConstructor
public enum LogicalSymbol implements Statement {

    TRUE(true),
    FALSE(false);

    private final boolean value;

    @Override
    public Statement evaluatedUnder(Model model) {
        return this;
    }

    @Override
    public Statement inCnf() {
        return this;
    }

    @Override
    public boolean isConjunctive() {
        return false;
    }

    @Override
    public boolean isDisjunctive() {
        return false;
    }

    @Override
    public String prettyPrinted() {
        return String.valueOf(value);
    }

    @Override
    public Iterator<Statement> iterator() {
        return Collections.emptyIterator();
    }
}
