package domain;

import java.util.ArrayList;

public abstract class AbstractDomain implements Domain {

    public static Domain intRange(int from, int toExcluding) {
        return new SimpleDomain(from, toExcluding);
    }

    public static AbstractDomain combine(Domain d1, Domain d2) {
        var components = new ArrayList<SimpleDomain>();
        for (int i = 0; i < d1.getNumberOfComponents(); i++) {
            components.add((SimpleDomain) d1.getComponent(i));
        }
        for (int i = 0; i < d2.getNumberOfComponents(); i++) {
            components.add((SimpleDomain) d2.getComponent(i));
        }
        return new CompositeDomain(components.toArray(new SimpleDomain[0]));
    }

    public int indexOfElement(DomainElement domainElement) {
        int i = 0;
        for (var element: this) {
            if (element.equals(domainElement)) return i;
            i++;
        }
        return -1;
    }

    public DomainElement elementForIndex(int index) {
        int i = 0;
        for (var element: this) {
            if (index == i) return element;
            i++;
        }
        return null;
    }
}
