import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population {
    private final List<Instance> instances;
    private final int size;
    private final Dataset dataset;

    public Population(int size, boolean isEmpty, Dataset dataset) {
        this.size = size;
        this.dataset = dataset;
        if (isEmpty) {
            this.instances = new ArrayList<>();
        } else {
            this.instances = generatePopulation(size);
        }

    }

    public List<Instance> getInstances() {
        return instances;
    }

    public List<Instance> getBestInstances(int numberOfInstances) {
        instances.sort(Comparator.comparingDouble(Instance::getMse));
        return instances.subList(0, numberOfInstances);
    }

    public void addInstance(Instance instance) {
        instances.add(instance);
    }

    public void addInstances(List<Instance> instances) {
        this.instances.addAll(instances);
    }

    public void removeInstance(Instance instance) {
        instances.remove(instance);
    }

    public boolean isFull() {
        if (instances.size() == size) {
            instances.sort(Comparator.comparingDouble(Instance::getMse));
            return true;
        } else {
            return false;
        }
    }

    public List<Instance> selectParents(int numberOfParents) {
        List<Instance> parents = new ArrayList<>();
        for (int i = 0; i < numberOfParents; i++) {
            parents.add(selectInstance());
        }
        return parents;
    }

    public List<Instance> threeTournamentSelection() {
        List<Instance> parents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            parents.add(randomInstance());
        }
        return parents;
    }

    public Instance randomInstance() {
        return instances.get((int) (Math.random() * instances.size()));
    }

    public Instance selectInstance() {
        return rouletteWheelSelection();
    }

    private Instance rouletteWheelSelection() {
        double totalFitness = instances.stream().mapToDouble(instance -> 1 / instance.getMse()).sum();
        double random = Math.random() * totalFitness;
        double current = 0;

        instances.sort(Comparator.comparingDouble(Instance::getMse));

        for (Instance instance : instances) {
            current += 1 / instance.getMse();
            if (current > random) {
                return instance;
            }
        }

        return instances.get(instances.size() - 1);
    }

    private List<Instance> generatePopulation(int size) {
        List<Instance> instances = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            instances.add(generateInstance());
        }
        return instances;
    }

    private Instance generateInstance() {
        var betas = new double[5];
        for (int i = 0; i < 5; i++) {
            betas[i] = Math.random() * 8 - 4;
        }
        return new Instance(betas, dataset);
    }
}
