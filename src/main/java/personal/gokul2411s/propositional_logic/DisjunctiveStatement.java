package personal.gokul2411s.propositional_logic;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
class DisjunctiveStatement implements Statement {

    @NonNull
    private final Statement statement1;

    @NonNull
    private final Statement statement2;

    @Override
    public Statement evaluatedUnder(Model model) {
        Statement statement1Evaluated = statement1.evaluatedUnder(model);
        Statement statement2Evaluated = statement2.evaluatedUnder(model);

        if (statement1Evaluated.equals(LogicalSymbol.TRUE) || statement2Evaluated.equals(LogicalSymbol.TRUE)) {
            return LogicalSymbol.TRUE;
        }
        if (statement1Evaluated.equals(LogicalSymbol.FALSE)) {
            return statement2Evaluated;
        }
        if (statement2Evaluated.equals(LogicalSymbol.FALSE)) {
            return statement1Evaluated;
        }

        return new DisjunctiveStatement(statement1Evaluated, statement2Evaluated);
    }

    @Override
    public String prettyPrinted() {
        return String.format("(%s OR %s)", statement1.prettyPrinted(), statement2.prettyPrinted());
    }
}
