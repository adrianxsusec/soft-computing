package hr.fer.sc.defuzzify;


import hr.fer.sc.set.FuzzySet;

public interface Defuzzifier {
    int defuzzify(FuzzySet fuzzySet);
}
