package hr.fer.sc.system.rules.langvar.second_try;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;

import static hr.fer.sc.set.StandardFuzzySets.gammaFunction;
import static hr.fer.sc.set.StandardFuzzySets.lFunction;

public class NewDirectionVariables {
    public static FuzzySet correct = new CalculatedFuzzySet(new SimpleDomain(0, 2), gammaFunction(0, 1));
    public static FuzzySet wrong = new CalculatedFuzzySet(new SimpleDomain(0, 2), lFunction(0, 1));
}
