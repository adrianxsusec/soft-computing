package domain;

public interface Domain extends Iterable<DomainElement> {
    int getCardinality();
    Domain getComponent(int index);
    int getNumberOfComponents();
    int indexOfElement(DomainElement domainElement);
    DomainElement elementForIndex(int index);
}
