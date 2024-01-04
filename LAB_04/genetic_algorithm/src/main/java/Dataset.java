import java.util.List;

public class Dataset {
    private final List<Double> x;
    private final List<Double> y;
    private final List<Double> output;

    public Dataset(List<Double> x, List<Double> y, List<Double> output) {
        this.x = x;
        this.y = y;
        this.output = output;
    }

    public List<Double> getX() {
        return x;
    }

    public List<Double> getY() {
        return y;
    }

    public List<Double> getOutput() {
        return output;
    }

}
