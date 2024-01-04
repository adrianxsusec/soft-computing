import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GeneticAlgorithm implements Algorithm {
    private final double mutationRate;
    private final int populationSize;
    private final int iterations;
    private final Dataset dataset;

    GeneticAlgorithm(double mutationRate, int populationSize, int iterations, Dataset dataset) {
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;
        this.iterations = iterations;
        this.dataset = dataset;
    }

    public void runCanonical(int elitism) {
        var initialPopulation = new Population(populationSize, false, dataset);
        var bestInstance = initialPopulation.getBestInstances(1).get(0);

        for (int i = 0; i < iterations; i++) {
            var newPopulation = new Population(populationSize, true, dataset);

            if (elitism > 0) {
                var bestInstances = initialPopulation.getBestInstances(elitism);
                newPopulation.addInstances(bestInstances);
            }

            while (!newPopulation.isFull()) {
                var parents = initialPopulation.selectParents(2);
                var offspring = parents.get(0).crossover(parents.get(1));
                offspring.mutate(mutationRate);
                newPopulation.addInstance(offspring);
            }

            initialPopulation = newPopulation;

            if (initialPopulation.getBestInstances(1).get(0).getMse() < bestInstance.getMse()) {
                bestInstance = initialPopulation.getBestInstances(1).get(0);
                System.out.println("Iteration: " + i + " MSE: " + bestInstance.getMse());
            }
        }

        System.out.println("Best instance: " + Arrays.toString(bestInstance.getBetas()));
    }

    public void runElimination() {
        var population = new Population(populationSize, false, dataset);
        var bestInstance = population.getBestInstances(1).get(0);

        for (int i = 0; i < iterations; i++) {
            var competitors = population.threeTournamentSelection();
            competitors.sort(Comparator.comparingDouble(Instance::getMse));
            var worst = competitors.get(2);
            var offspring = competitors.get(0).crossover(competitors.get(1));
            offspring.mutate(mutationRate);
            population.removeInstance(worst);
            population.addInstance(offspring);

            if (population.getBestInstances(1).get(0).getMse() < bestInstance.getMse()) {
                bestInstance = population.getBestInstances(1).get(0);
                System.out.println("Iteration: " + i + " MSE: " + bestInstance.getMse());
            }
        }

        System.out.println("Best instance: " + Arrays.toString(bestInstance.getBetas()));
    }
}
