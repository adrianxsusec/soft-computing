package hr.fer.sc.util;

import hr.fer.sc.domain.Debug;
import hr.fer.sc.domain.DomainElement;
import hr.fer.sc.operation.Operations;
import hr.fer.sc.system.rules.langvar.input.DistanceVariables;
import hr.fer.sc.system.rules.langvar.output.AngleVariables;
import hr.fer.sc.system.rules.Rule;

import java.util.Set;

public class TestRule {
    public static void main(String[] args) {

        var inputName = InputName.LEFT;
        var antecedentVariable = DistanceVariables.close;
        var consequenceVariable = AngleVariables.right_big;

        var inputValue = 100;

        var rule = new Rule().addNamedAntecedent(inputName, antecedentVariable)
                .addConsequence(consequenceVariable);

        var inputs = Set.of(
                new Input(inputName, inputValue)
        );

        var conclusion = rule.conclude(Operations.zadehAnd(), inputs);

        System.out.println(antecedentVariable.getValueAt(DomainElement.of(inputValue)));

        Debug.print(antecedentVariable, "Antecedent variable");

        Debug.print(conclusion, "conclusion");

        System.out.println("the end");
    }
}
