package hr.fer.sc.system.min;


import hr.fer.sc.defuzzify.COADefuzzifier;
import hr.fer.sc.system.rules.Rule;
import hr.fer.sc.util.Input;

import java.util.Set;

public class AccelerationFuzzySystemMin extends FuzzySystemMin {

    public AccelerationFuzzySystemMin(COADefuzzifier defuzzifier, Set<Rule> rules) {
        super(defuzzifier, rules);
    }

    @Override
    public int conclude(Set<Input> inputs) {
        var conclusions = rules.stream().map((rule -> rule.conclude(t_norm, inputs))).toList();
        var finalConclusion = Rule.combine(conclusions, s_norm);
        return defuzzifier.defuzzify(finalConclusion);
    }
}
