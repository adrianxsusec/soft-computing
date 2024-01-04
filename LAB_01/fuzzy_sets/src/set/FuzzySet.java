package set;
import domain.Domain;
import domain.DomainElement;

public interface FuzzySet {
    Domain getDomain();
    double getValueAt(DomainElement domainElement);
}
