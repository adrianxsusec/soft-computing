package hr.fer.sc.domain;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleDomain extends AbstractDomain {

    private final int first;
    private final int last;

    public SimpleDomain(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last - 1;
    }

    public SimpleDomain[] getAllComponents() {
        return new SimpleDomain[]{ this };
    }

    @Override
    public int getCardinality() {
        return last - first;
    }

    @Override
    public Domain getComponent(int index) {
        return this;
    }

    @Override
    public int getNumberOfComponents() {
        return 1;
    }

    public Iterator<DomainElement> iterator() {
        return new Iterator<>() {
            private int curr = first;

            @Override
            public boolean hasNext() {
                return curr < last;
            }

            @Override
            public DomainElement next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int[] elementValues = { curr++ };
                return DomainElement.of(elementValues);
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleDomain that = (SimpleDomain) o;
        return first == that.first && last == that.last;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }
}
