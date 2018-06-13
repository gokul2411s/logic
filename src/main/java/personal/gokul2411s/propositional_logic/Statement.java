package personal.gokul2411s.propositional_logic;

import java.util.Collections;
import java.util.Iterator;

/**
 * Represents a statement in propositional logic.
 *
 * <p>A statement is also an iterable over its sub-statements (if any).</p>
 */
public interface Statement extends Iterable<Statement> {

    /**
     * Returns a simplified version of the statement after assigning logical values to
     * all symbols in the statement.
     */
    Statement evaluatedUnder(Model model);

    /**
     * Returns the statement in Conjunctive Normal Form (CNF).
     */
    Statement inCnf();

    /**
     * Returns whether this statement represents a conjunction of sub-statements.
     */
    boolean isConjunctive();

    /**
     * Returns whether this statement represents a disjunction of sub-statements.
     */
    boolean isDisjunctive();

    /**
     * Gets a readable version of the statement.
     */
    String prettyPrinted();
}
