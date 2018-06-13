package personal.gokul2411s.propositional_logic;

/**
 * Represents a statement in propositional logic.
 */
public interface Statement {

    Statement evaluatedUnder(Model model);

    String prettyPrinted();
}
