package personal.gokul2411s.propositional_logic;

public final class StatementFactory {

    public static Symbol symbol(String value) {
        return new Symbol(value);
    }

    public static Statement conjuncted(Statement statement1, Statement statement2) {
        return new ConjunctiveStatement(statement1, statement2);
    }

    public static Statement disjuncted(Statement statement1, Statement statement2) {
        return new DisjunctiveStatement(statement1, statement2);
    }

    public static Statement negated(Statement statement) {
        return new NegativeStatement(statement);
    }

    private StatementFactory() { }
}
