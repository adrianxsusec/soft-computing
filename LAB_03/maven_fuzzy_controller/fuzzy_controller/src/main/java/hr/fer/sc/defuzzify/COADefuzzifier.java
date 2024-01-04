package hr.fer.sc.defuzzify;

import hr.fer.sc.set.FuzzySet;

public class COADefuzzifier implements Defuzzifier {

    public int defuzzify(FuzzySet fuzzySet) {
        var sumUp = 0.0;
        var sumDown = 0.0;
        for (var element : fuzzySet.getDomain()) {
            sumUp += fuzzySet.getValueAt(element) * element.getComponentValue(0);
            sumDown += fuzzySet.getValueAt(element);
        }
        return (int) Math.round(sumUp / sumDown);
    }
}
