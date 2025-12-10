GPTree.java - import java.util.Random;

public class GPTree implements Comparable<GPTree>, Cloneable {

    private Node root;
    private double fitness;

    public GPTree() {
        this.root = new Node();
    }

    public GPTree(int maxDepth, Random rand) {
        this.root = new Node();
    }



    public double eval(double x) {
        if (root == null) return 0.0;
        return root.eval(x);
    }
   
    @Override
public String toString() {
    if (root == null) return "(null)";
    return root.toString();
}


    public void evalFitness(DataSet dataSet) {
        double sumSqError = 0;
        for (DataRow row : dataSet) {
            double predicted = eval(row.getX());
            double actual = row.getY();
            double diff = predicted - actual;
            sumSqError += diff * diff;
        }
        fitness = sumSqError;
    }

    public double getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(GPTree t) {
        return Double.compare(this.fitness, t.fitness);
    }

    @Override
    public Object clone() {
        try {
            GPTree copy = (GPTree) super.clone();
            copy.root = (Node) root.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // placeholder crossover
    public void crossover(GPTree other) {
        // TODO: implement real crossover
    }
}