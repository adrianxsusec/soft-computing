package hr.fer.sc.system.product;

import hr.fer.sc.defuzzify.Defuzzifier;
import hr.fer.sc.operation.BinaryFunction;
import hr.fer.sc.operation.Operations;
import hr.fer.sc.system.FuzzySystem;
import hr.fer.sc.system.rules.Rule;
import hr.fer.sc.util.Input;

import java.util.Set;

public class FuzzySystemProduct implements FuzzySystem {
    protected final BinaryFunction t_norm;
    protected final BinaryFunction s_norm;
    protected final Defuzzifier defuzzifier;
    protected final Set<Rule> rules;

    public FuzzySystemProduct(Defuzzifier defuzzifier, Set<Rule> rules) {
        this.t_norm = Operations.product();
        this.s_norm = Operations.zadehOr();
        this.defuzzifier = defuzzifier;
        this.rules = rules;
    }

    @Override
    public int conclude(Set<Input> inputs) {
        var conclusions = rules.stream().map((rule -> rule.conclude(t_norm, inputs))).toList();
        var finalConclusion = Rule.combine(conclusions, s_norm);
        return defuzzifier.defuzzify(finalConclusion);
    }
}
