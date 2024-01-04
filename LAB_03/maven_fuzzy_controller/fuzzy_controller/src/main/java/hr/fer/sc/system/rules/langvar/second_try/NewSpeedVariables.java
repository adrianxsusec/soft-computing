package hr.fer.sc.system.rules.langvar.second_try;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;

import static hr.fer.sc.set.StandardFuzzySets.*;

public class NewSpeedVariables {
    public static FuzzySet slow = new CalculatedFuzzySet(new SimpleDomain(0, 120), lFunction(15, 30));
    public static FuzzySet medium = new CalculatedFuzzySet(new SimpleDomain(0, 120), lambdaFunction(30, 60, 90));
    public static FuzzySet fast = new CalculatedFuzzySet(new SimpleDomain(0, 120), gammaFunction(50, 70));
}

