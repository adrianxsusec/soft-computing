package hr.fer.sc.system.rules.langvar.output;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;

import static hr.fer.sc.set.StandardFuzzySets.*;

public class AccelerationVariables {
    public static FuzzySet negative = new CalculatedFuzzySet(new SimpleDomain(-30, 40), lFunction(5, 10));
    public static FuzzySet small = new CalculatedFuzzySet(new SimpleDomain(-20, 40), lambdaFunction(15, 20, 25));
    public static FuzzySet big = new CalculatedFuzzySet(new SimpleDomain(-20, 40), gammaFunction(15, 40));
}
