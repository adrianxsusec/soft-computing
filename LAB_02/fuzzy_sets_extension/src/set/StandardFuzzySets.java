package set;

public class StandardFuzzySets {

    public static IntUnaryFunction lFunction(int alpha, int beta) {
        return (x) -> {
            if (x < alpha) return 1;
            if (x >= beta) return 0;
            return (double) (beta - x) / (beta - alpha);
        };
    }

    public static IntUnaryFunction gammaFunction(int alpha, int beta) {
        return (x) -> {
            if (x < alpha) return 0;
            if (x >= beta) return 1;
            return (double) (x - alpha) / (beta - alpha);
        };
    }

    public static IntUnaryFunction lambdaFunction(int alpha, int beta, int gamma) {
        return (x) -> {
            if (x < alpha) return 0;
            if (x >= gamma) return 0;
            if (alpha <= x && x < beta) return (double) (x - alpha) / (beta - alpha);
            return (double) (gamma - x) / (gamma - beta);
        };
    }

}
