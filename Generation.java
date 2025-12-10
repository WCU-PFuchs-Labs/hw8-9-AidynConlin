Generation.java- import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;


public class Generation {
    private GPTree[] trees;
    private DataSet data;
    private Random rand = new Random();

    public Generation(int size, int maxDepth, String fileName) {
        data = new DataSet(fileName);
        trees = new GPTree[size];
        for (int i = 0; i < size; i++) {
            trees[i] = new GPTree(maxDepth, rand); // assuming GPTree has such a constructor
        }
    }

    public void evalAll() {
        for (GPTree tree : trees) {
            tree.evalFitness(data);
        }
        Arrays.sort(trees);
    }

    public ArrayList<GPTree> getTopTen() {
        ArrayList<GPTree> top = new ArrayList<>();
        for (int i = 0; i < 10 && i < trees.length; i++) {
            top.add(trees[i]);
        }
        return top;
    }

    public void printBestFitness() {
        System.out.printf("Best Fitness: %.2f%n", trees[0].getFitness());
    }

    public void printBestTree() {
        System.out.println("Best Tree: " + trees[0]);
    }

    // Checkpoint 2
    public void evolve() {
        GPTree[] nextGen = new GPTree[trees.length];
        for (int i = 0; i < trees.length / 2; i++) {
            // Pick two parents among more fit ones
            GPTree parent1 = trees[rand.nextInt(Math.max(1, trees.length / 4))];
            GPTree parent2 = trees[rand.nextInt(Math.max(1, trees.length / 4))];

            // Clone and crossover
            GPTree child1 = (GPTree) parent1.clone();
            GPTree child2 = (GPTree) parent2.clone();
            child1.crossover(child2); // assuming GPTree has crossover()
            nextGen[2*i] = child1;
            nextGen[2*i + 1] = child2;
        }
        trees = nextGen;
    }
}