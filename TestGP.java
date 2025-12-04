public class TestGP {
    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: java TestGP <csvfile>");
            return;
        }

        DataSet ds = DataSet.loadCSV(args[0]);

        double bestEver = Double.MAX_VALUE;
        double totalBest = 0;

        int runs = 10;

        for (int r = 1; r <= runs; r++) {
            Generation g = new Generation(50, 5, ds);

            for (int gen = 0; gen < 20; gen++)
                g = g.nextGen();

            g.evalAll();
            g.sort();
            double best = g.best().fitness;

            System.out.println("Run " + r + ": " + best);

            bestEver = Math.min(bestEver, best);
            totalBest += best;
        }

        System.out.println("\nBest overall: " + bestEver);
        System.out.println("Average best: " + (totalBest / runs));
    }
}
