package domain;

import set.FuzzySet;

public class Debug {
    public static void print(Domain domain, String headingText) {
        if (headingText != null) {
            System.out.println(headingText);
        }
        for (DomainElement e : domain) {
            System.out.println("Element domene: " + e);
        }
        System.out.println("Kardinalitet domene je: " + domain.getCardinality());
        System.out.println();
    }

    public static void print(FuzzySet fuzzySet, String headingText) {
        if (headingText != null) {
            System.out.println(headingText);
        }
        for (DomainElement e : fuzzySet.getDomain()) {
            System.out.printf("d(%s)=%f%n", e, fuzzySet.getValueAt(e));
        }
    }
}
