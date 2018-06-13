package personal.gokul2411s.propositional_logic;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Collections;
import java.util.Iterator;

@Value
@AllArgsConstructor
public class Symbol implements Statement {

    @NonNull
    private final String value;

    @Override
    public Statement evaluatedUnder(Model model) {
        LogicalSymbol logicalSymbol = model.assignment(this);
        if (logicalSymbol != null) {
            // there is a logical symbol assigned to this symbol in the model.
            return logicalSymbol;
        }
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
        return value;
    }

    @Override
    public Iterator<Statement> iterator() {
        return Collections.emptyIterator();
    }
}
