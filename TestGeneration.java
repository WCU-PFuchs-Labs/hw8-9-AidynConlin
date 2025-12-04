public class TestGeneration {
    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: java TestGeneration <csvfile>");
            return;
        }

        DataSet ds = DataSet.loadCSV(args[0]);

        Generation g = new Generation(50, 5, ds);

        for (int i = 0; i < 30; i++) {
            g.evalAll();
            g.sort();

            System.out.println("Generation " + i);
            System.out.println("Best fitness: " + g.best().fitness);
            System.out.println("Best tree: " + g.best());
            System.out.println();

            g = g.nextGen();
        }
    }
}
