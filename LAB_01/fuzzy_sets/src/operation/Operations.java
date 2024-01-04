package operation;

import set.FuzzySet;
import set.MutableFuzzySet;

import java.util.InputMismatchException;

public class Operations {
    public static FuzzySet unaryOperation(FuzzySet set, UnaryFunction unaryFunction) {
        var domain = set.getDomain();
        var mutableSet = new MutableFuzzySet(domain);
        for (var element: domain) {
            mutableSet.set(element, unaryFunction.valueAt(set.getValueAt(element)));
        }
        return mutableSet;
    }

    public static FuzzySet binaryOperation(FuzzySet set1, FuzzySet set2, BinaryFunction binaryFunction) {
        if (!set1.getDomain().equals(set2.getDomain())) {
            throw new InputMismatchException();
        }
        var domain = set1.getDomain();
        var mutableSet = new MutableFuzzySet(domain);
        for (var element: domain) {
            mutableSet.set(element, binaryFunction.valueAt(set1.getValueAt(element), set2.getValueAt(element)));
        }
        return mutableSet;
    }

    public static UnaryFunction zadehNot() {
        return x -> 1 - x;
    }

    public static BinaryFunction zadehAnd() {
        return Math::min;
    }

    public static BinaryFunction zadehOr() {
        return Math::max;
    }

    public static BinaryFunction hamacherTNorm(double v) {
        if (v < 0) throw new ParameterException("Parameter can not be less than 0");
        return (x, y) -> (x * y) / v + (1 - v) * (x + y - x * y);
    }

    public static BinaryFunction hamacherSNorm(double v) {
        if (v < 0) throw new ParameterException("Parameter can not be less than 0");
        return (x, y) -> (x + y - (2 - v) * x * y) / (1 - (1 - v) * x * y);
    }

    static class ParameterException extends RuntimeException {
        public ParameterException(String message) {
            super(message);
        }
    }
}
