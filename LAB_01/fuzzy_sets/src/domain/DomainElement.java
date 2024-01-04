package domain;

import java.util.Arrays;
import java.util.StringJoiner;

public class DomainElement {
    private final int[] values;

    public DomainElement(int ...values) {
        this.values = values;
    }

    public int getNumberOfComponents() {
        return values.length;
    }

    public int getComponentValue(int index) {
        return values[index];
    }

    public static DomainElement of(int ...elements) {
        return new DomainElement(elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainElement that = (DomainElement) o;
        return Arrays.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        if (values.length == 0) {
            return "";
        } else if (values.length == 1) {
            return Integer.toString(values[0]);
        } else {
            StringJoiner joiner = new StringJoiner(", ", "(", ")");
            for (int value : values) {
                joiner.add(Integer.toString(value));
            }
            return joiner.toString();
        }
    }
}
