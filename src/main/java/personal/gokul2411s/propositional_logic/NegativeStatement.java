package personal.gokul2411s.propositional_logic;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor
class NegativeStatement implements Statement {

    @NonNull
    private final Statement statement;

    @Override
    public Statement evaluatedUnder(Model model) {
        Statement statementEvaluated = statement.evaluatedUnder(model);
        if (statementEvaluated.equals(LogicalSymbol.TRUE)) {
            return LogicalSymbol.FALSE;
        } else if (statementEvaluated.equals(LogicalSymbol.FALSE)) {
            return LogicalSymbol.TRUE;
        }
        return new NegativeStatement(statementEvaluated);
    }

    @Override
    public String prettyPrinted() {
        return String.format("(NOT %s)", statement.prettyPrinted());
    }
}
