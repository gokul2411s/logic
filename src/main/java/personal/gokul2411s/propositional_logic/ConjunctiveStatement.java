package personal.gokul2411s.propositional_logic;

import com.google.common.base.Joiner;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
class ConjunctiveStatement implements Statement {

    @NonNull
    private final List<Statement> statements;

    ConjunctiveStatement(Statement statement1, Statement statement2, Statement... others) {
        statements = new ArrayList<>();
        statements.add(statement1);
        statements.add(statement2);
        statements.addAll(Arrays.asList(others));
    }

    @Override
    public Statement evaluatedUnder(Model model) {
        List<Statement> evaluatedStatements = new ArrayList<>();
        for (Statement statement : statements) {
            Statement evaluatedStatement = statement.evaluatedUnder(model);
            if (evaluatedStatement.equals(LogicalSymbol.FALSE)) {
                return LogicalSymbol.FALSE;
            }
            if (!evaluatedStatement.equals(LogicalSymbol.TRUE)) {
                evaluatedStatements.add(statement.evaluatedUnder(model));
            }
        }
        if (evaluatedStatements.isEmpty()) {
            return LogicalSymbol.TRUE;
        } else if (evaluatedStatements.size() == 1) {
            return evaluatedStatements.get(0);
        }

        Statement[] otherEvaluatedStatements = new Statement[evaluatedStatements.size() - 2];
        otherEvaluatedStatements =
                IntStream.range(2, evaluatedStatements.size())
                        .mapToObj(i -> evaluatedStatements.get(i))
                        .collect(Collectors.toList())
                        .toArray(otherEvaluatedStatements);
        return new ConjunctiveStatement(
                evaluatedStatements.get(0),
                evaluatedStatements.get(1),
                otherEvaluatedStatements);
    }

    @Override
    public String prettyPrinted() {
        String joined =
                Joiner.on(" AND ").join(
                        statements.stream()
                                .map(s -> s.prettyPrinted())
                                .collect(Collectors.toList()));
        return "(" + joined + ")";
    }
}
