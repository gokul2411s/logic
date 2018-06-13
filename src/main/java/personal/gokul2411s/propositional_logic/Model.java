package personal.gokul2411s.propositional_logic;

import com.google.common.collect.ImmutableMap;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

/**
 * A set of assigments of logical values to symbols.
 */
@Value
@Builder
public class Model {

    @Singular
    private final ImmutableMap<Symbol, LogicalSymbol> assignments;

    public LogicalSymbol assignment(Symbol symbol) {
        return assignments.get(symbol);
    }
}
