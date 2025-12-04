import java.util.Random;

public class Genner {
    private static Random r = new Random();

    public static int randInt(int lo, int hi) {
        return lo + r.nextInt(hi - lo + 1);
    }

    public static double randDouble(double lo, double hi) {
        return lo + (hi - lo) * r.nextDouble();
    }

    public static boolean chance(double p) {
        return r.nextDouble() < p;
    }

    public static Node.OpType randomOp() {
        Node.OpType[] ops = Node.OpType.values();
        return ops[r.nextInt(ops.length)];
    }
}
