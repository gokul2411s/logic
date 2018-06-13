package personal.gokul2411s.propositional_logic;

public final class StatementFactory {

    public static Symbol symbol(String value) {
        return new Symbol(value);
    }

    public static Statement conjuncted(
            Statement statement1, Statement statement2, Statement... others) {
        return new ConjunctiveStatement(statement1, statement2, others);
    }

    public static Statement disjuncted(Statement statement1, Statement statement2, Statement... others) {
        return new DisjunctiveStatement(statement1, statement2, others);
    }

    public static Statement negated(Statement statement) {
        return new NegativeStatement(statement);
    }

    public static Statement implication(Statement statement1, Statement statement2) {
        return disjuncted(negated(statement1), statement2);
    }

    public static Statement twoWayImplication(Statement statement1, Statement statement2) {
        return conjuncted(implication(statement1, statement2), implication(statement2, statement1));
    }

    private StatementFactory() { }
}
