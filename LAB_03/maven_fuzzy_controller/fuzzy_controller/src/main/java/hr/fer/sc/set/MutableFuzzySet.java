package hr.fer.sc.set;

import hr.fer.sc.domain.Domain;
import hr.fer.sc.domain.DomainElement;

public class MutableFuzzySet implements FuzzySet{
    private final double[] memberships;
    private final Domain domain;

    public MutableFuzzySet(Domain domain) {
        this.domain = domain;
        this.memberships = new double[domain.getCardinality()];
    }

    public Domain getDomain() {
        return domain;
    }

    public MutableFuzzySet set(DomainElement domainElement, double value) {
        memberships[domain.indexOfElement(domainElement)] = value;
        return this;
    }

    @Override
    public double getValueAt(DomainElement domainElement) {
        var index = domain.indexOfElement(domainElement);
        if (index == -1) {
            return 0;
        }
        return memberships[index];
    }
}
