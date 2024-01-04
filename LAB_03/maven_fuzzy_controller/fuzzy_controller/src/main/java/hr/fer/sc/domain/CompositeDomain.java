package hr.fer.sc.domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeDomain extends AbstractDomain {
    private final SimpleDomain[] components;

    public CompositeDomain(SimpleDomain[] components) {
        this.components = components;
    }


    public Iterator<DomainElement> iterator() {
        return new Iterator<>() {
            final List<Iterator<DomainElement>> iteratorList = Arrays.stream(components).map(SimpleDomain::iterator).collect(Collectors.toList());
//            final int[] values = Arrays.stream(components).mapToInt(SimpleDomain::getFirst).toArray();

            final int[] values = iteratorList.stream().mapToInt(it -> it.next().getComponentValue(0)).toArray();
            boolean endReached = false;

            @Override
            public boolean hasNext() {
                var shouldContinue = !endReached;
                checkEndReached();
                return shouldContinue;
            }

            @Override
            public DomainElement next() {
                var domainElement = DomainElement.of(Arrays.copyOf(values, values.length));

                if (endReached) return domainElement;

                int lastIndex = iteratorList.size() - 1;
                incrementIterator(lastIndex);
                return domainElement;
            }

            private void checkEndReached() {
                if (allMaxedOut()) endReached = true;
            }

            private void incrementIterator(int index) {
                var iterator = iteratorList.get(index);
                if (iterator.hasNext()) {
                    var nextValue = iterator.next().getComponentValue(0);
                    setValue(index, nextValue);
                } else {
                    resetIterator(index);
                    incrementIterator(index - 1);
                    resetValue(index);
                }
            }

            private void resetIterator(int index) {
                iteratorList.set(index, components[index].iterator());
                iteratorList.get(index).next();
            }

            private void resetValue(int index) {
                values[index] = components[index].iterator().next().getComponentValue(0);
            }

            private void setValue(int index, int next) {
                values[index] = next;
            }

            private boolean allMaxedOut() {
                for (var iterator: iteratorList) {
                    if (iterator.hasNext()) return false;
                }
                return true;
            }
        };
    }

    @Override
    public int getCardinality() {
        if (components.length == 0) return 0;
        int cardinality = 1;
        for (var simpleDomain : components) {
            cardinality *= simpleDomain.getCardinality();
        }
        return cardinality;
    }

    @Override
    public Domain getComponent(int index) {
        return components[index];
    }

    @Override
    public int getNumberOfComponents() {
        return components.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeDomain that = (CompositeDomain) o;
        return Arrays.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}
