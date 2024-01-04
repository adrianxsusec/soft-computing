import domain.Debug;
import domain.SimpleDomain;
import set.CalculatedFuzzySet;
import set.StandardFuzzySets;

public class Main2 {
    public static void main(String[] args) {
        var big = new CalculatedFuzzySet(new SimpleDomain(1, 5), StandardFuzzySets.lFunction(1,3));
        Debug.print(big, "b");
    }
}
