package hr.fer.sc.util;

import hr.fer.sc.domain.Debug;
import hr.fer.sc.set.FuzzySet;
import hr.fer.sc.system.rules.langvar.input.DirectionVariables;

public class TestDebugger {
    public static void main(String[] args) {
        FuzzySet var = DirectionVariables.wrong;
        Debug.print(var, "wrong");
    }
}
