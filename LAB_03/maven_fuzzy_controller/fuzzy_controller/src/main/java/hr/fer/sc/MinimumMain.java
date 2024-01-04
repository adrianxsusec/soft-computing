package hr.fer.sc;

import hr.fer.sc.defuzzify.COADefuzzifier;
import hr.fer.sc.operation.Operations;
import hr.fer.sc.system.min.FuzzySystemMin;
import hr.fer.sc.system.product.FuzzySystemProduct;
import hr.fer.sc.system.rules.AccelerationRules;
import hr.fer.sc.system.rules.HelmRules;
import hr.fer.sc.system.rules.langvar.second_try.rules.NewAccelerationRules;
import hr.fer.sc.system.rules.langvar.second_try.rules.NewHelmRules;
import hr.fer.sc.util.InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class MinimumMain {
    public static void main(String[] args) throws IOException {
        var defuzzifier = new COADefuzzifier();

        var accelerationMinimumRules = AccelerationRules.getMinimumRules();
        var helmMinimumRules = HelmRules.getMinimumRules();

        var accelerationProductRules = AccelerationRules.getProductRules();
        var helmProductRules = HelmRules.getProductRules();

        var newHelmRules = NewHelmRules.getMinimumRules();
        var newAccelerationRules = NewAccelerationRules.getMinimumRules();

        var fuzzyAcceleration = new FuzzySystemMin(defuzzifier, newAccelerationRules);
        var fuzzyHelm = new FuzzySystemMin(defuzzifier, newHelmRules);

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        int l = 0, d = 0, lk = 0, dk = 0, v = 0, s = 0;
        String line = null;

        while (true) {
            if ((line = inputReader.readLine()) != null) {
                if (line.charAt(0) == 'K') {
                    break;
                }
                Scanner scanner = new Scanner(line);
                l = scanner.nextInt();
                d = scanner.nextInt();
                lk = scanner.nextInt();
                dk = scanner.nextInt();
                v = scanner.nextInt();
                s = scanner.nextInt();
            }

            var inputs = InputParser.namedInputs(l, d, lk, dk, v, s);
            var acceleration = fuzzyAcceleration.conclude(inputs);
            var helm = fuzzyHelm.conclude(inputs);

            System.out.println(acceleration + " " + helm);
            System.out.flush();
        }
    }
}
