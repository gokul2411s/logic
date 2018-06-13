package personal.gokul2411s.propositional_logic;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

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
    public String prettyPrinted() {
        return value;
    }
}
