package hr.fer.sc.system.rules.langvar.second_try;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;

import static hr.fer.sc.set.StandardFuzzySets.*;

public class NewDistanceVariables {
    public static FuzzySet close = new CalculatedFuzzySet(new SimpleDomain(0, 1301), lFunction(30, 55));
    public static FuzzySet medium = new CalculatedFuzzySet(new SimpleDomain(0, 1301), lambdaFunction(35, 60, 120));
    public static FuzzySet far = new CalculatedFuzzySet(new SimpleDomain(0, 1301), gammaFunction(100, 150));
}
