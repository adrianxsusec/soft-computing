package hr.fer.sc.system.rules.langvar.second_try.rules;

import hr.fer.sc.domain.SimpleDomain;
import hr.fer.sc.set.CalculatedFuzzySet;
import hr.fer.sc.set.FuzzySet;
import hr.fer.sc.system.rules.Rule;
import hr.fer.sc.system.rules.langvar.second_try.NewDistanceVariables;
import hr.fer.sc.system.rules.langvar.second_try.NewSpeedVariables;
import hr.fer.sc.util.InputName;

import java.util.Set;

import static hr.fer.sc.set.StandardFuzzySets.*;
import static hr.fer.sc.util.InputName.*;

public class NewAccelerationRules {

    public static FuzzySet negative_big = new CalculatedFuzzySet(new SimpleDomain(-60, 60), lFunction(35, 50));
    public static FuzzySet negative_small = new CalculatedFuzzySet(new SimpleDomain(-60, 60), lambdaFunction(45, 52, 60));
    public static FuzzySet positive_small = new CalculatedFuzzySet(new SimpleDomain(-60, 60), lambdaFunction(60, 80, 100));
    public static FuzzySet positive_big = new CalculatedFuzzySet(new SimpleDomain(-60, 60), gammaFunction(90, 110));

    // need to add rules that say that you have to go faster if you're slow, and slow down if you're going fast
    // also if you're in the middle of the channel, start going faster
    // if you're close to the wall, slow down
    // if you're close to the wall and going fast, slow down even more
    // if you're close to the wall and going slow, speed up I GUESS

    // not sure if there need to be too many rules, but I guess we'll see

    // so far the new 2 helm rules are working quite nicely with the old acceleration rules

    public static Set<Rule> getMinimumRules() {
        return Set.of(
                // close to shore
                new Rule().addNamedAntecedent(LEFT_ANGLED, NewDistanceVariables.close)
                        .addConsequence(negative_big),

                new Rule().addNamedAntecedent(RIGHT_ANGLED, NewDistanceVariables.close)
                        .addConsequence(negative_big),

                new Rule().addNamedAntecedent(LEFT_ANGLED, NewDistanceVariables.medium)
                        .addConsequence(negative_small),

                new Rule().addNamedAntecedent(RIGHT_ANGLED, NewDistanceVariables.medium)
                        .addConsequence(negative_small),

                // if you're going too slow
                new Rule().addNamedAntecedent(SPEED, NewSpeedVariables.slow)
                        .addConsequence(positive_big),

                new Rule().addNamedAntecedent(SPEED, NewSpeedVariables.medium)
                        .addConsequence(positive_small),

                // if you're going too fast
                new Rule().addNamedAntecedent(SPEED, NewSpeedVariables.fast)
                        .addConsequence(negative_big),

                // if you're in the middle of the channel
                new Rule().addNamedAntecedent(LEFT_ANGLED, NewDistanceVariables.close)
                        .addNamedAntecedent(RIGHT_ANGLED, NewDistanceVariables.close)
                        .addConsequence(positive_big),

                new Rule().addNamedAntecedent(LEFT_ANGLED, NewDistanceVariables.medium)
                        .addNamedAntecedent(RIGHT_ANGLED, NewDistanceVariables.medium)
                        .addConsequence(positive_big),

                new Rule().addNamedAntecedent(LEFT, NewDistanceVariables.close)
                        .addNamedAntecedent(RIGHT, NewDistanceVariables.close)
                        .addConsequence(positive_small)
        );
    }

}
