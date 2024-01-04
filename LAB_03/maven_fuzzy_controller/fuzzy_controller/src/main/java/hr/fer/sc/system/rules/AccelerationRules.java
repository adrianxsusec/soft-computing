package hr.fer.sc.system.rules;


import hr.fer.sc.system.rules.langvar.input.DirectionVariables;
import hr.fer.sc.system.rules.langvar.input.DistanceVariables;
import hr.fer.sc.system.rules.langvar.input.SpeedVariables;
import hr.fer.sc.system.rules.langvar.output.AccelerationVariables;

import java.util.Set;

import static hr.fer.sc.util.InputName.*;

public class AccelerationRules {

    public static Set<Rule> newMinimumRules() {
        return Set.of(

        );
    }

    public static Set<Rule> getMinimumRules() {
        return Set.of(
                // Slow down if you're close to something
                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
                        .addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                // if in close quarters, go faster
                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
                        .addNamedAntecedent(RIGHT, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.big),

                // about to crash angled
                new Rule().addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                // Accelerate if you're going in the right direction
                new Rule().addNamedAntecedent(DIRECTION, DirectionVariables.correct)
                        .addConsequence(AccelerationVariables.small),

                // Start going fast if you're far from something
                new Rule().addNamedAntecedent(LEFT, DistanceVariables.medium)
                        .addNamedAntecedent(RIGHT, DistanceVariables.medium)
                        .addConsequence(AccelerationVariables.small),

                // Start going at medium distance
                new Rule().addNamedAntecedent(LEFT, DistanceVariables.medium)
                        .addNamedAntecedent(RIGHT, DistanceVariables.medium)
                        .addConsequence(AccelerationVariables.small),

                new Rule().addNamedAntecedent(LEFT_ANGLED, DistanceVariables.far)
                        .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.far)
                        .addConsequence(AccelerationVariables.big),

                // Start accelerating if you're going slow
                new Rule().addNamedAntecedent(SPEED, SpeedVariables.small)
                        .addConsequence(AccelerationVariables.big),

                new Rule().addNamedAntecedent(SPEED, SpeedVariables.medium)
                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(SPEED, SpeedVariables.big)
                        .addConsequence(AccelerationVariables.negative)
        );
    }

    public static Set<Rule> getProductRules() {
        return Set.of(
                // Slow down if you're close to something
//                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
//                        .addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
//                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),
//
                new Rule().addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

//                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.close)
//                        .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
//                        .addConsequence(AccelerationVariables.negative),

                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),
//
                new Rule().addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.negative),

                // Start going if you're far from something
                new Rule().addNamedAntecedent(LEFT, DistanceVariables.medium)
                        .addNamedAntecedent(RIGHT, DistanceVariables.medium)
                        .addConsequence(AccelerationVariables.small),

                // Start accelerating if you're going slow
                new Rule().addNamedAntecedent(SPEED, SpeedVariables.small)
                        .addNamedAntecedent(DIRECTION, DirectionVariables.correct)
                        .addConsequence(AccelerationVariables.small),

                new Rule().addNamedAntecedent(SPEED, SpeedVariables.medium)
                        .addNamedAntecedent(DIRECTION, DirectionVariables.correct)
                        .addConsequence(AccelerationVariables.big),

                // Accelerate if you're going in the right direction
                new Rule().addNamedAntecedent(DIRECTION, DirectionVariables.correct)
                        .addConsequence(AccelerationVariables.small),

                // Accelerate if you're going in the right direction and you're going slow
                new Rule().addNamedAntecedent(DIRECTION, DirectionVariables.correct)
                        .addNamedAntecedent(SPEED, SpeedVariables.small)
                        .addConsequence(AccelerationVariables.big),

//              if both are close, add small acceleration
                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
                        .addNamedAntecedent(RIGHT, DistanceVariables.close)
                        .addConsequence(AccelerationVariables.small),

                // if you are close to the left side, but right side is kind of far, add small acceleration
                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close).
                        addNamedAntecedent(RIGHT, DistanceVariables.medium).
                        addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.medium).
                        addConsequence(AccelerationVariables.big),

                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close).
                        addNamedAntecedent(RIGHT, DistanceVariables.medium).
                        addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close).
                        addConsequence(AccelerationVariables.small),

                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
                        .addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(RIGHT, DistanceVariables.medium)
                        .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.medium)
                        .addConsequence(AccelerationVariables.small),

                // if you are close to the right side, but left side is kind of far, add small acceleration
                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.close).
                        addNamedAntecedent(LEFT, DistanceVariables.medium).
                        addNamedAntecedent(LEFT_ANGLED, DistanceVariables.medium).
                        addConsequence(AccelerationVariables.big),

                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.close).
                        addNamedAntecedent(LEFT, DistanceVariables.medium).
                        addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close).
                        addConsequence(AccelerationVariables.small),

                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(LEFT, DistanceVariables.medium)
                        .addNamedAntecedent(LEFT_ANGLED, DistanceVariables.medium)
                        .addConsequence(AccelerationVariables.small)
//
//                new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
//                        .addNamedAntecedent(RIGHT, DistanceVariables.close)
//                        .addNamedAntecedent(LEFT_ANGLED, DistanceVariables.medium)
//                        .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.medium)
//                        .addConsequence(AccelerationVariables.small)

//                new Rule().addNamedAntecedent(DIRECTION, DirectionVariables.correct)
//                        .addNamedAntecedent(SPEED, SpeedVariables.small)
//                        .addConsequence(AccelerationVariables.medium),
//
//                new Rule().addNamedAntecedent(DIRECTION, DirectionVariables.correct)
//                        .addNamedAntecedent(SPEED, SpeedVariables.medium)
//                        .addConsequence(AccelerationVariables.negative)

//                new Rule().addNamedAntecedent(LEFT, DistanceVariables.medium)
//                        .addConsequence(AccelerationVariables.medium),
//
//                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.medium)
//                        .addConsequence(AccelerationVariables.medium)

//            new Rule().addNamedAntecedent(LEFT, DistanceVariables.close)
//                    .addNamedAntecedent(LEFT_ANGLED, DistanceVariables.close)
//                    .addNamedAntecedent(SPEED, SpeedVariables.small)
//                    .addConsequence(AccelerationVariables.small),
//
//                new Rule().addNamedAntecedent(RIGHT, DistanceVariables.close)
//                    .addNamedAntecedent(RIGHT_ANGLED, DistanceVariables.close)
//                    .addNamedAntecedent(SPEED, SpeedVariables.small)
//                    .addConsequence(AccelerationVariables.small)
        );
    }
}
