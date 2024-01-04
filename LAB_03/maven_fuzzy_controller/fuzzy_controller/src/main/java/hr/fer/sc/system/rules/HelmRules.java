package hr.fer.sc.system.rules;

import hr.fer.sc.system.rules.langvar.input.DirectionVariables;
import hr.fer.sc.system.rules.langvar.input.DistanceVariables;
import hr.fer.sc.system.rules.langvar.output.AngleVariables;
import hr.fer.sc.util.InputName;

import java.util.Set;

public class HelmRules {

    public static Set<Rule> getMinimumRules() {
        return Set.of(
                // RIGHT TURNS
                new Rule().addNamedAntecedent(InputName.RIGHT, DistanceVariables.close)
                        .addConsequence(AngleVariables.left_big),

                new Rule().addNamedAntecedent(InputName.RIGHT, DistanceVariables.medium)
                        .addConsequence(AngleVariables.left_medium),

                new Rule().addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AngleVariables.left_big),

                new Rule().addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.medium)
                        .addConsequence(AngleVariables.left_medium),

                new Rule().addNamedAntecedent(InputName.RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AngleVariables.left_big),


                // LEFT TURNS
                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.close)
                        .addConsequence(AngleVariables.right_big),

                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.medium)
                        .addConsequence(AngleVariables.right_medium),

                new Rule().addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addConsequence(AngleVariables.right_big),

                new Rule().addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.medium)
                        .addConsequence(AngleVariables.right_medium),

                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addConsequence(AngleVariables.right_big),


                // LEFT ANGLED SPECIFIC
                new Rule().addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addConsequence(AngleVariables.right_big),

//                new Rule().addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.medium)
//                        .addConsequence(AngleVariables.right_small),
//
//                new Rule().addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.far)
//                        .addConsequence(AngleVariables.right_small),

                // RIGHT ANGLED SPECIFIC
                new Rule().addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AngleVariables.left_big),

//                new Rule().addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.medium)
//                        .addConsequence(AngleVariables.left_small),
//
//                new Rule().addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.far)
//                        .addConsequence(AngleVariables.left_small),


                // GO STRAIGHT
                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addConsequence(AngleVariables.straight)
        );
    }

    public static Set<Rule> getProductRules() {
        return Set.of(
                // LEFT TURNS
                new Rule().addNamedAntecedent(InputName.RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.left_big),
                new Rule().addNamedAntecedent(InputName.RIGHT, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.left_medium),

                new Rule().addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.left_big),
                new Rule().addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.left_medium),

                new Rule().addNamedAntecedent(InputName.RIGHT, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.left_big),

                new Rule().addNamedAntecedent(InputName.RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.left_big),

                // RIGHT TURNS
                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.right_big),
                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.right_medium),

                new Rule().addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.right_big),
                new Rule().addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.right_medium),

                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.right_big),

                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.right_big),

                // GO STRAIGHT
                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.straight),

//                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.medium)
//                        .addNamedAntecedent(InputName.RIGHT, DistanceVariables.medium)
//                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.medium)
//                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.medium)
//                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
//                        .addConsequence(AngleVariables.straight),

                // if direction is wrong, turn around
                new Rule().addNamedAntecedent(InputName.DIRECTION, DirectionVariables.wrong)
                        .addConsequence(AngleVariables.left_big),

                // complex turns
                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT, DistanceVariables.close)
                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.straight),

                new Rule().addNamedAntecedent(InputName.LEFT, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.RIGHT, DistanceVariables.medium)
                        .addNamedAntecedent(InputName.LEFT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.RIGHT_ANGLED, DistanceVariables.close)
                        .addNamedAntecedent(InputName.DIRECTION, DirectionVariables.correct)
                        .addConsequence(AngleVariables.straight)

        );
    }
}
