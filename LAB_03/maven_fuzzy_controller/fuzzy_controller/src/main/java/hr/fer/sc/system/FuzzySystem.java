package hr.fer.sc.system;

import hr.fer.sc.util.*;

import java.util.Set;

public interface FuzzySystem {
    int conclude(Set<Input> inputs);
}
