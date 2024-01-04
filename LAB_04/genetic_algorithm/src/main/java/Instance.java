public class Instance {
    private final double[] betas;
    private final double mse;
    private final Dataset dataset;

    public Instance(double[] betas, Dataset dataset) {
        this.betas = betas;
        this.dataset = dataset;
        this.mse = calculateMse(dataset);
    }

    public double[] getBetas() {
        return betas;
    }

    public double getMse() {
        return mse;
    }

    private double calculateMse(Dataset dataset) {
        double sum = 0;
        for (int i = 0; i < dataset.getX().size(); i++) {
            double x = dataset.getX().get(i);
            double y = dataset.getY().get(i);
            double output = dataset.getOutput().get(i);
            sum += Math.pow(output - calculateOutput(x, y), 2);
        }
        return sum / dataset.getX().size();
    }

    public double calculateOutput(double x, double y) {
        return Math.sin(betas[0] + betas[1] * x) + betas[2] * Math.cos(x * (betas[3] + y)) / (1 + Math.exp(Math.pow(x - betas[4], 2)));
    }

    public Instance crossover(Instance other) {
        double[] betas = new double[5];
        for (int i = 0; i < 5; i++) {
            betas[i] = discreteRecombination(this.getBetas()[i], other.getBetas()[i]);
        }
        return new Instance(betas, dataset);
    }

    public void mutate(double mutationRate) {
        for (int i = 0; i < 5; i++) {
            if (Math.random() < mutationRate) {
                betas[i] = Math.random() * (4 - (-4)) + (-4);
            }
        }
    }

    private double discreteRecombination(double a, double b) {
        return Math.random() < 0.5 ? a : b;
    }
}
