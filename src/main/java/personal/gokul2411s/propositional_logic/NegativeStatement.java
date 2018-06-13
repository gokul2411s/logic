package personal.gokul2411s.propositional_logic;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public Statement inCnf() {
        // Apply De-Morgan's laws.
        // 1. (NOT (A AND B)) = ((NOT A) OR  (NOT B))
        // 2. (NOT (A OR  B)) = ((NOT A) AND (NOT B))
        Statement statementInCnf = statement.inCnf();
        if (statementInCnf.isConjunctive() || statementInCnf.isDisjunctive()) {
            List<Statement> subStatements = new ArrayList<>();
            statementInCnf.iterator().forEachRemaining(s -> subStatements.add(s));

            Statement[] otherNegatedSubStatements = new Statement[subStatements.size() - 2];
            otherNegatedSubStatements =
                    IntStream.range(2, subStatements.size())
                            .mapToObj(i -> new NegativeStatement(subStatements.get(i)))
                            .collect(Collectors.toList())
                            .toArray(otherNegatedSubStatements);

            if (statementInCnf.isConjunctive()) {
                return new DisjunctiveStatement(
                        new NegativeStatement(subStatements.get(0)),
                        new NegativeStatement(subStatements.get(1)),
                        otherNegatedSubStatements);
            }

            return new ConjunctiveStatement(
                    new NegativeStatement(subStatements.get(0)),
                    new NegativeStatement(subStatements.get(1)),
                    otherNegatedSubStatements);

        }
        return new NegativeStatement(statementInCnf);
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
        return String.format("(NOT %s)", statement.prettyPrinted());
    }

    @Override
    public Iterator<Statement> iterator() {
        return Arrays.asList(statement).iterator();
    }
}
