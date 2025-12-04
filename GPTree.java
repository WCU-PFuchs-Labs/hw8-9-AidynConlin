import java.util.*;

public class GPTree implements Comparable<GPTree> {

    private Node root;
    public double fitness;
    private int maxDepth;

    public GPTree(int maxDepth, int numVars) {
        this.maxDepth = maxDepth;
        this.root = randomTree(0, maxDepth, numVars);
    }

    private GPTree(Node n, int maxDepth) {
        this.root = n;
        this.maxDepth = maxDepth;
    }

    // Build random tree with depth control
    private Node randomTree(int depth, int maxDepth, int numVars) {
        if (depth == maxDepth)
            return Node.var(Genner.randInt(0, numVars - 1));

        if (Genner.chance(0.2))
            return Node.constant(Genner.randDouble(-5, 5));

        Node.OpType op = Genner.randomOp();
        return Node.op(op,
                randomTree(depth + 1, maxDepth, numVars),
                randomTree(depth + 1, maxDepth, numVars));
    }

    // Evaluate tree on one row
    public double eval(DataRow row) {
        return evalNode(root, row);
    }

    private double evalNode(Node n, DataRow row) {
        switch (n.type) {
        case VAR:
            return row.get(n.varIndex);

        case CONST:
            return n.constValue;

        case OP:
            double a = evalNode(n.left, row);
            double b = evalNode(n.right, row);

            switch (n.op) {
            case ADD: return a + b;
            case SUB: return a - b;
            case MUL: return a * b;
            case DIV: return (Math.abs(b) < 1e-9) ? a : a / b;
            }
        }
        return 0;
    }

    // Compute average absolute error
    public void evalFitness(DataSet ds) {
        double sum = 0;
        for (int i = 0; i < ds.size(); i++) {
            DataRow r = ds.getRow(i);
            double guess = eval(r);
            sum += Math.abs(guess - r.getTarget());
        }
        fitness = sum / ds.size();
    }

    // Mutation: randomly modify a subtree
    public void mutate() {
        if (Genner.chance(0.5)) {
            root = randomTree(0, maxDepth, maxDepth);
        }
    }

    // Deep clone
    public GPTree clone() {
        return new GPTree(cloneNode(root), maxDepth);
    }

    private Node cloneNode(Node n) {
        if (n == null) return null;

        Node c = new Node(n.type);
        c.varIndex = n.varIndex;
        c.constValue = n.constValue;
        c.op = n.op;
        c.left = cloneNode(n.left);
        c.right = cloneNode(n.right);

        return c;
    }

    @Override
    public int compareTo(GPTree o) {
        return Double.compare(this.fitness, o.fitness);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof GPTree) && this.toString().equals(o.toString());
    }

    @Override
    public String toString() {
        return print(root);
    }

    private String print(Node n) {
        if (n.type == Node.Type.VAR) return "x" + n.varIndex;
        if (n.type == Node.Type.CONST) return String.format("%.2f", n.constValue);
        return "(" + print(n.left) + " " + n.op.toString() + " " + print(n.right) + ")";
    }
}
