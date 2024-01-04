package hr.fer.sc.system.rules.langvar.second_try.rules;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;
import hr.fer.sc.system.rules.Rule;
import hr.fer.sc.system.rules.langvar.second_try.NewDistanceVariables;
import hr.fer.sc.util.InputName;

import java.util.Set;

import static hr.fer.sc.set.StandardFuzzySets.*;
import static hr.fer.sc.util.InputName.*;

public class NewHelmRules {

    public static FuzzySet left_small = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(90, 110, 130));
    public static FuzzySet left_medium = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(110, 130, 150));
    public static FuzzySet left_big = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(130, 150, 170));

    public static FuzzySet right_small = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(50, 70, 90));
    public static FuzzySet right_medium = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(30, 50, 70));
    public static FuzzySet right_big = new CalculatedFuzzySet(new SimpleDomain(-90, 91), lambdaFunction(10, 30, 50));

    public static Set<Rule> getMinimumRules() {
        return Set.of(
                // close to shore
                new Rule().addNamedAntecedent(LEFT_ANGLED, NewDistanceVariables.close)
                        .addConsequence(right_big),

                new Rule().addNamedAntecedent(RIGHT_ANGLED, NewDistanceVariables.close)
                        .addConsequence(left_big)
        );
    }

}
