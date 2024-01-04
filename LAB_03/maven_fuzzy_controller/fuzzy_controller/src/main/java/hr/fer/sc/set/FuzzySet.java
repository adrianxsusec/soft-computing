package hr.fer.sc.set;
import hr.fer.sc.domain.Domain;
import hr.fer.sc.domain.DomainElement;

public interface FuzzySet {
    Domain getDomain();
    double getValueAt(DomainElement domainElement);
}
