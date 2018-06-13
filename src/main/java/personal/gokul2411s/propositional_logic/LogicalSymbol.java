package personal.gokul2411s.propositional_logic;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Represents a logical symbol, corresponding to either true or false.
 *
 * <p>This class allows treating logical values as first-class statements.</p>
 */
@AllArgsConstructor
public enum LogicalSymbol implements Statement {

    TRUE(true),
    FALSE(false);

    @NonNull
    private final boolean value;

    @Override
    public Statement evaluatedUnder(Model model) {
        return this;
    }

    @Override
    public String prettyPrinted() {
        return String.valueOf(value);
    }
}
