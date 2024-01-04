package set;

import domain.Domain;
import domain.DomainElement;

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
        return intUnaryFunction.valueAt(domain.indexOfElement(domainElement));
    }
}
