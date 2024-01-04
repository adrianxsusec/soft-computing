package hr.fer.sc.system.rules.langvar.output;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;

import static hr.fer.sc.set.StandardFuzzySets.*;

public class AngleVariables {

    public static FuzzySet right_small = new CalculatedFuzzySet(new SimpleDomain(-90, 91), gammaFunction(70, 80));
    public static FuzzySet right_medium = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(65, 75, 90));
    public static FuzzySet right_big = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lFunction(60, 65));


    public static FuzzySet left_small = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lFunction(100, 110));
    public static FuzzySet left_medium = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(100, 110, 120));
    public static FuzzySet left_big = new CalculatedFuzzySet(new SimpleDomain(-90, 91), gammaFunction(110, 115));


    public static FuzzySet straight = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(80, 90, 100));
}
