import java.util.*;

public class Generation {

    private ArrayList<GPTree> pop = new ArrayList<>();
    private int maxDepth;
    private int populationSize;
    private DataSet ds;

    public Generation(int size, int maxDepth, DataSet ds) {
        this.populationSize = size;
        this.maxDepth = maxDepth;
        this.ds = ds;

        for (int i = 0; i < size; i++)
            pop.add(new GPTree(maxDepth, ds.getNumVars()));
    }

    public void evalAll() {
        for (GPTree t : pop)
            t.evalFitness(ds);
    }

    public void sort() {
        Collections.sort(pop);
    }

    public GPTree best() {
        return pop.get(0);
    }

    public Generation nextGen() {
        evalAll();
        sort();

        Generation next = new Generation(0, maxDepth, ds);

        int eliteCount = Math.max(1, populationSize / 20); // top 5%

        // Elitism
        for (int i = 0; i < eliteCount; i++)
            next.pop.add(pop.get(i).clone());

        // Fill remainder with mutated clones
        while (next.pop.size() < populationSize) {
            GPTree parent = pop.get(Genner.randInt(0, eliteCount - 1)).clone();
            parent.mutate();
            next.pop.add(parent);
        }

        return next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GPTree t : pop)
            sb.append(String.format("Fit: %.4f  Tree: %s\n", t.fitness, t));
        return sb.toString();
    }
}
