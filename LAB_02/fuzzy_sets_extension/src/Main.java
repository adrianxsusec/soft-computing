import domain.AbstractDomain;
import domain.Debug;
import domain.Domain;
import domain.DomainElement;
import operation.Operations;
import set.CalculatedFuzzySet;
import set.FuzzySet;
import set.MutableFuzzySet;
import set.StandardFuzzySets;
import relation.Relations;

public class Main {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        check1Lab();
        check2Lab();
        var end = System.currentTimeMillis();
        System.out.println(Math.abs(start - end));
    }

    private static void check2Lab() {
        // check 1. dio
        Domain u = AbstractDomain.intRange(1, 6); // {1,2,3,4,5}
        Domain u2 = AbstractDomain.combine(u, u);
        FuzzySet r1 = new MutableFuzzySet(u2)
                .set(DomainElement.of(1,1), 1)
                .set(DomainElement.of(2,2), 1)
                .set(DomainElement.of(3,3), 1)
                .set(DomainElement.of(4,4), 1)
                .set(DomainElement.of(5,5), 1)
                .set(DomainElement.of(3,1), 0.5)
                .set(DomainElement.of(1,3), 0.5);
        FuzzySet r2 = new MutableFuzzySet(u2)
                .set(DomainElement.of(1,1), 1)
                .set(DomainElement.of(2,2), 1)
                .set(DomainElement.of(3,3), 1)
                .set(DomainElement.of(4,4), 1)
                .set(DomainElement.of(5,5), 1)
                .set(DomainElement.of(3,1), 0.5)
                .set(DomainElement.of(1,3), 0.1);
        FuzzySet r3 = new MutableFuzzySet(u2)
                .set(DomainElement.of(1,1), 1)
                .set(DomainElement.of(2,2), 1)
                .set(DomainElement.of(3,3), 0.3)
                .set(DomainElement.of(4,4), 1)
                .set(DomainElement.of(5,5), 1)
                .set(DomainElement.of(1,2), 0.6)
                .set(DomainElement.of(2,1), 0.6)
                .set(DomainElement.of(2,3), 0.7)
                .set(DomainElement.of(3,2), 0.7)
                .set(DomainElement.of(3,1), 0.5)
                .set(DomainElement.of(1,3), 0.5);
        FuzzySet r4 = new MutableFuzzySet(u2)
                .set(DomainElement.of(1,1), 1)
                .set(DomainElement.of(2,2), 1)
                .set(DomainElement.of(3,3), 1)
                .set(DomainElement.of(4,4), 1)
                .set(DomainElement.of(5,5), 1)
                .set(DomainElement.of(1,2), 0.4)
                .set(DomainElement.of(2,1), 0.4)
                .set(DomainElement.of(2,3), 0.5)
                .set(DomainElement.of(3,2), 0.5)
                .set(DomainElement.of(1,3), 0.4)
                .set(DomainElement.of(3,1), 0.4);

        boolean test1 = Relations.isUTimesURelation(r1);
        System.out.println("r1 je definiran nad UxU? "+test1);

        boolean test2 = Relations.isSymmetric(r1);
        System.out.println("r1 je simetrična? "+test2);

        boolean test3 = Relations.isSymmetric(r2);
        System.out.println("r2 je simetrična? "+test3);

        boolean test4 = Relations.isReflexive(r1);
        System.out.println("r1 je refleksivna? "+test4);

        boolean test5 = Relations.isReflexive(r3);
        System.out.println("r3 je refleksivna? "+test5);

        boolean test6 = Relations.isMaxMinTransitive(r3);
        System.out.println("r3 je max-min tranzitivna? "+test6);

        boolean test7 = Relations.isMaxMinTransitive(r4);
        System.out.println("r4 je max-min tranzitivna? "+test7);

        // check 2. dio
        Domain u11 = AbstractDomain.intRange(1, 5); // {1,2,3,4}
        Domain u22 = AbstractDomain.intRange(1, 4); // {1,2,3}
        Domain u33 = AbstractDomain.intRange(1, 5); // {1,2,3,4}
        FuzzySet r11 = new MutableFuzzySet(AbstractDomain.combine(u11, u22))
                .set(DomainElement.of(1,1), 0.3)
                .set(DomainElement.of(1,2), 1)
                .set(DomainElement.of(3,3), 0.5)
                .set(DomainElement.of(4,3), 0.5);
        FuzzySet r22 = new MutableFuzzySet(AbstractDomain.combine(u22, u33))
                .set(DomainElement.of(1,1), 1)
                .set(DomainElement.of(2,1), 0.5)
                .set(DomainElement.of(2,2), 0.7)
                .set(DomainElement.of(3,3), 1)
                .set(DomainElement.of(3,4), 0.4);
        FuzzySet r1r2 = Relations.compositionOfBinaryRelations(r11, r22);

        for(DomainElement e : r1r2.getDomain()) {
            System.out.println("mu("+e+")="+r1r2.getValueAt(e));
        }

        Domain u6 = AbstractDomain.intRange(1, 5); // {1,2,3,4}
        FuzzySet r = new MutableFuzzySet(AbstractDomain.combine(u6, u6))
                .set(DomainElement.of(1,1), 1)
                .set(DomainElement.of(2,2), 1)
                .set(DomainElement.of(3,3), 1)
                .set(DomainElement.of(4,4), 1)
                .set(DomainElement.of(1,2), 0.3)
                .set(DomainElement.of(2,1), 0.3)
                .set(DomainElement.of(2,3), 0.5)
                .set(DomainElement.of(3,2), 0.5)
                .set(DomainElement.of(3,4), 0.2)
                .set(DomainElement.of(4,3), 0.2);
        FuzzySet r6 = r;
        System.out.println(
                "Početna relacija je neizrazita relacija ekvivalencije? " +
                        Relations.isFuzzyEquivalence(r6));
        System.out.println();
        for(int i = 1; i <= 3; i++) {
            r6 = Relations.compositionOfBinaryRelations(r6, r);
            System.out.println(
                    "Broj odrađenih kompozicija: " + i + ". Relacija je:");
            for(DomainElement e : r6.getDomain()) {
                System.out.println("mu("+e+")="+r6.getValueAt(e));
            }
            System.out.println(
                    "Ova relacija je neizrazita relacija ekvivalencije? " +
                            Relations.isFuzzyEquivalence(r6));
            System.out.println();
        }
    }

    private static void check1Lab() {
        // check 1. dio
        System.out.println("Check 1. dio");
        var d1 = AbstractDomain.intRange(0, 2);
        Debug.print(d1, "Elementi simple domene d1");
        System.out.println(d1.indexOfElement(DomainElement.of(2)));
        System.out.println(d1.elementForIndex(1));
        System.out.println();

        var d2 = AbstractDomain.intRange(0, 10);
        Debug.print(d2, "Elementi simple domene d2");

        var d3 = AbstractDomain.combine(d1, d2);
        Debug.print(d3, "Elementi composite domene d3");

        var d4 = AbstractDomain.combine(d1, d3);
        Debug.print(d4, "Elementi super domene d4");

        System.out.println(d4.elementForIndex(0));
        System.out.println(d4.elementForIndex(1));
        System.out.println(d4.elementForIndex(39));

        System.out.println(d4.indexOfElement(DomainElement.of(1, 1, 8)));
        System.out.println(d4.indexOfElement(DomainElement.of(1, 1, 9)));

        // check 2. dio
        System.out.println("Check 2. dio");
        var d = AbstractDomain.intRange(0, 11);

        FuzzySet set1 = new MutableFuzzySet(d)
                .set(DomainElement.of(0), 1.0)
                .set(DomainElement.of(1), 0.8)
                .set(DomainElement.of(2), 0.6)
                .set(DomainElement.of(3), 0.4)
                .set(DomainElement.of(4), 0.2);
        Debug.print(set1, "Set1:");

        var d5 = AbstractDomain.intRange(-5, 6); // {-5,-4,...,4,5}
        var set2 = new CalculatedFuzzySet(
                d5,
                StandardFuzzySets.lambdaFunction(
                        d5.indexOfElement(DomainElement.of(-4)),
                        d5.indexOfElement(DomainElement.of(0)),
                        d5.indexOfElement(DomainElement.of(4))
                )
        );
        Debug.print(set2, "Set2:");

        // check 3. dio
        System.out.println("Check 3. dio");

        Debug.print(set1, "Set1:");
        var notSet1 = Operations.unaryOperation(set1, Operations.zadehNot());
        Debug.print(notSet1, "notSet1:");

        var union = Operations.binaryOperation(set1, notSet1, Operations.zadehOr());
        Debug.print(union, "Set1 union notSet1:");
        var hinters = Operations.binaryOperation(set1, notSet1, Operations.hamacherTNorm(1.0));
        Debug.print(hinters, "Set1 intersection with notSet1 using parameterised Hamacher T norm with parameter 1.0:");
    }
}