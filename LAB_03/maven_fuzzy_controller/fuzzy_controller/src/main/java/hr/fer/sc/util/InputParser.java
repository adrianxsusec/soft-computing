package hr.fer.sc.util;

import java.util.Set;

import static hr.fer.sc.util.InputName.*;


public class InputParser {
    public static Set<Input> namedInputs(int leftDistance, int rightDistance, int leftAngled, int rightAngled, int speed, int direction) {
        return Set.of(
                new Input(LEFT, leftDistance),
                new Input(RIGHT, rightDistance),
                new Input(LEFT_ANGLED, leftAngled),
                new Input(RIGHT_ANGLED, rightAngled),
                new Input(SPEED, speed),
                new Input(DIRECTION, direction)
        );
    }
}
