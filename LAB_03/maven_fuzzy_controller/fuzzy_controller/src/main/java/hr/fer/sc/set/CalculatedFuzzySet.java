package hr.fer.sc.set;

import hr.fer.sc.domain.Domain;
import hr.fer.sc.domain.DomainElement;

public class CalculatedFuzzySet implements FuzzySet {

    private final Domain domain;
    private final IntUnaryFunction intUnaryFunction;

    public CalculatedFuzzySet(Domain domain, IntUnaryFunction intUnaryFunction) {
        this.domain = domain;
        this.intUnaryFunction = intUnaryFunction;
    }

    @Override
    public Domain getDomain() {
        return domain;
    }

    @Override
    public double getValueAt(DomainElement domainElement) {
        if (domain.indexOfElement(domainElement) == -1) {
            return 0;
        }
        return intUnaryFunction.valueAt(domain.indexOfElement(domainElement));
    }
}
