public class Main {
    public static void main(String[] args) {
        var dataset = TxtParser.parse("src/main/resources/zad4-dataset1.txt");
        var algorithm = new GeneticAlgorithm(0.1, 100, 10000, dataset);
        algorithm.runCanonical(1);
    }
}
