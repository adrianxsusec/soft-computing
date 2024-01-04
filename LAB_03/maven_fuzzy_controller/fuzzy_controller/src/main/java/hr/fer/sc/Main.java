package hr.fer.sc;

import hr.fer.sc.domain.AbstractDomain;
import hr.fer.sc.domain.Debug;
import hr.fer.sc.domain.DomainElement;
import hr.fer.sc.operation.Operations;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;
import hr.fer.sc.set.MutableFuzzySet;
import hr.fer.sc.set.StandardFuzzySets;

public class Main {
    public static void main(String[] args) {
//         check 1. dio
//        System.out.println("Check 1. dio");
//        var d1 = AbstractDomain.intRange(0, 2);
//        Debug.print(d1, "Elementi simple domene d1");
//        System.out.println(d1.indexOfElement(DomainElement.of(2)));
//        System.out.println(d1.elementForIndex(1));
//        System.out.println();
//
//        var d2 = AbstractDomain.intRange(0, 10);
//        Debug.print(d2, "Elementi simple domene d2");
//
//        var d3 = AbstractDomain.combine(d1, d2);
//        Debug.print(d3, "Elementi composite domene d3");
//
//        var d4 = AbstractDomain.combine(d1, d3);
//        Debug.print(d4, "Elementi super domene d4");
//
//        System.out.println(d4.elementForIndex(0));
//        System.out.println(d4.elementForIndex(1));
//        System.out.println(d4.elementForIndex(39));
//
//        System.out.println(d4.indexOfElement(DomainElement.of(1, 1, 8)));
//        System.out.println(d4.indexOfElement(DomainElement.of(1, 1, 9)));
//
//        // check 2. dio
//        System.out.println("Check 2. dio");
//        var d = AbstractDomain.intRange(0, 11);
//
//        FuzzySet set1 = new MutableFuzzySet(d)
//                .set(DomainElement.of(0), 1.0)
//                .set(DomainElement.of(1), 0.8)
//                .set(DomainElement.of(2), 0.6)
//                .set(DomainElement.of(3), 0.4)
//                .set(DomainElement.of(4), 0.2);
//        Debug.print(set1, "Set1:");
//
//        var d5 = AbstractDomain.intRange(-5, 6); // {-5,-4,...,4,5}
//        var set2 = new CalculatedFuzzySet(
//                d5,
//                StandardFuzzySets.lambdaFunction(
//                        d5.indexOfElement(DomainElement.of(-4)),
//                        d5.indexOfElement(DomainElement.of(0)),
//                        d5.indexOfElement(DomainElement.of(4))
//                )
//        );
//        Debug.print(set2, "Set2:");
//
//        // check 3. dio
//        System.out.println("Check 1. dio");
//
//        Debug.print(set1, "Set1:");
//        var notSet1 = Operations.unaryOperation(set1, Operations.zadehNot());
//        Debug.print(notSet1, "notSet1:");
//
//        var union = Operations.binaryOperation(set1, notSet1, Operations.zadehOr());
//        Debug.print(union, "Set1 union notSet1:");
//        var hinters = Operations.binaryOperation(set1, notSet1, Operations.hamacherTNorm(1.0));
//        Debug.print(hinters, "Set1 intersection with notSet1 using parameterised Hamacher T norm with parameter 1.0:");
    }
}