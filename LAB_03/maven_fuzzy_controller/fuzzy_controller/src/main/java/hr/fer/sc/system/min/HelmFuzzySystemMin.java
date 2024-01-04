package hr.fer.sc.system.min;

import hr.fer.sc.defuzzify.Defuzzifier;
import hr.fer.sc.system.rules.Rule;
import hr.fer.sc.util.Input;

import java.util.Set;

public class HelmFuzzySystemMin extends FuzzySystemMin {

    public HelmFuzzySystemMin(Defuzzifier defuzzifier, Set<Rule> rules) {
        super(defuzzifier, rules);
    }

    @Override
    public int conclude(Set<Input> inputs) {
        var conclusions = rules.stream().map((rule -> rule.conclude(t_norm, inputs))).toList();
        if (conclusions.isEmpty()) {
            return 0;
        }
        var finalConclusion = Rule.combine(conclusions, s_norm);
        return defuzzifier.defuzzify(finalConclusion);
    }
}
