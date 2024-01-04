package hr.fer.sc.system.rules.langvar.input;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;

import static hr.fer.sc.set.StandardFuzzySets.*;

public class SpeedVariables {
    public static FuzzySet small = new CalculatedFuzzySet(new SimpleDomain(0, 100), lFunction(25, 40));
    public static FuzzySet medium = new CalculatedFuzzySet(new SimpleDomain(0, 100), lambdaFunction(35, 45, 55));
    public static FuzzySet big = new CalculatedFuzzySet(new SimpleDomain(0, 100), gammaFunction(45, 55));
}
