package hr.fer.sc.system.rules;



import hr.fer.sc.domain.DomainElement;
import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.operation.BinaryFunction;
import hr.fer.sc.set.FuzzySet;
import hr.fer.sc.set.MutableFuzzySet;
import hr.fer.sc.util.Input;
import hr.fer.sc.util.InputName;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Rule {
    private final List<FuzzySet> antecedents;
    private final EnumMap<InputName, FuzzySet> namedAntecedents;
    private FuzzySet consequence;

    public Rule() {
        this.antecedents = new ArrayList<>();
        this.namedAntecedents = new EnumMap<>(InputName.class);
    }

    public Rule addAntecedent(FuzzySet antecedent) {
        this.antecedents.add(antecedent);
        return this;
    }

    public Rule addConsequence(FuzzySet consequence) {
        this.consequence = consequence;
        return this;
    }

    public Rule addNamedAntecedent(InputName inputName, FuzzySet antecedent) {
        this.namedAntecedents.put(inputName, antecedent);
        return this;
    }

    public FuzzySet getNamedAntecedent(InputName inputName) {
        return this.namedAntecedents.getOrDefault(inputName, new MutableFuzzySet(new SimpleDomain(0,1)));
    }

    public FuzzySet conclude(BinaryFunction t_norm, Set<Input> inputs) {
        var conclusion = new MutableFuzzySet(consequence.getDomain());
        for (var domainElement : conclusion.getDomain()) {
            var consequenceValue = consequence.getValueAt(domainElement);

//            var calculated = inputs.stream()
//                    .map(input -> {
//                        if (namedAntecedents.containsKey(input.name())) {
//                            return namedAntecedents.get(input.name()).getValueAt(DomainElement.of(input.value()));
//                        }
//                        return 1.0;
//                    }).reduce(t_norm::valueAt);

            var calculatedAntecedent = inputs.stream()
                    .filter(input -> namedAntecedents.containsKey(input.name()))
                    .map(input -> namedAntecedents.get(input.name()).getValueAt(DomainElement.of(input.value())))
                    .reduce(t_norm::valueAt);
            var finalCalculation = t_norm.valueAt(calculatedAntecedent.get(), consequenceValue);
            conclusion.set(domainElement, finalCalculation);
        }
        return conclusion;
    }

//    public FuzzySet conclude(int l, int d, int lk, int dk, int v, int s, BinaryFunction t_norm) {
//        var conclusion = new MutableFuzzySet(consequence.getDomain());
//
//        var l_value = antecedents.get(0).getValueAt(DomainElement.of(l));
//        var d_value = antecedents.get(1).getValueAt(DomainElement.of(d));
//        var lk_value = antecedents.get(2).getValueAt(DomainElement.of(lk));
//        var dk_value = antecedents.get(3).getValueAt(DomainElement.of(dk));
//        var v_value = antecedents.get(4).getValueAt(DomainElement.of(v));
//        var s_value = antecedents.get(5).getValueAt(DomainElement.of(s));
//
//        for (var domainElement : conclusion.getDomain()) {
//            var consequenceValue = consequence.getValueAt(domainElement);
//            var min = Stream.of(l_value, d_value, lk_value, dk_value, v_value, s_value, consequenceValue).reduce(t_norm::valueAt);
//            conclusion.set(domainElement, min.get());
//        }
//
//        return conclusion;
//    }

    public FuzzySet concludeHardCoded(BinaryFunction t_norm) {
        var conclusion = new MutableFuzzySet(consequence.getDomain());

        var l_value = antecedents.get(0).getValueAt(DomainElement.of(3));
        var d_value = antecedents.get(1).getValueAt(DomainElement.of(2));

        for (var domainElement : conclusion.getDomain()) {
            var consequenceValue = consequence.getValueAt(domainElement);
            var min = Stream.of(l_value, d_value, consequenceValue).reduce(t_norm::valueAt);
            conclusion.set(domainElement, min.get());
        }

        return conclusion;
    }

    public static FuzzySet combine(List<FuzzySet> conclusions, BinaryFunction combineOperation) {
        var combination = new MutableFuzzySet(conclusions.get(0).getDomain());

        for (var domainElement : combination.getDomain()) {
            combination.set(domainElement, conclusions.stream().map((e -> e.getValueAt(domainElement))).reduce(combineOperation::valueAt).get());
        }

        return combination;
    }
}
