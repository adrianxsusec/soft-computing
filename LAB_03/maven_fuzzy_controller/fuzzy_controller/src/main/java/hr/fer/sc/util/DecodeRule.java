package hr.fer.sc.util;

import hr.fer.sc.defuzzify.COADefuzzifier;
import hr.fer.sc.domain.Debug;
import hr.fer.sc.domain.DomainElement;
import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.operation.Operations;
import hr.fer.sc.set.MutableFuzzySet;
import hr.fer.sc.system.rules.Rule;

public class DecodeRule {
    public static void main(String[] args) {
//        var big = new MutableFuzzySet(new SimpleDomain(1, 5));
//        big.set(DomainElement.of(1), 0)
//                .set(DomainElement.of(2), 0.3)
//                .set(DomainElement.of(3), 0.7)
//                .set(DomainElement.of(4), 1);
//
//        var small = new MutableFuzzySet(new SimpleDomain(1, 5));
//        small.set(DomainElement.of(1), 1)
//                .set(DomainElement.of(2), 0.7)
//                .set(DomainElement.of(3), 0.3)
//                .set(DomainElement.of(4), 0);
//
//        var smallish = new MutableFuzzySet(new SimpleDomain(1, 5));
//        smallish.set(DomainElement.of(1), 1)
//                .set(DomainElement.of(2), 0.9)
//                .set(DomainElement.of(3), 0.5)
//                .set(DomainElement.of(4), 0);
//
//        var rule = new Rule();
//        rule.addAntecedent(big).addAntecedent(small).addConsequence(smallish);
//        var conclusion = rule.concludeHardCoded(Operations.zadehAnd());
//        Debug.print(conclusion, "");
//
//        var defuzzifier = new COADefuzzifier();
//        System.out.println(defuzzifier.defuzzify(conclusion));

        var rule = new Rule();
    }
}
