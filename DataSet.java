import java.util.*;
import java.io.*;

public class DataSet {

    private ArrayList<DataRow> rows = new ArrayList<>();
    private int numVars;

    public static DataSet loadCSV(String filename) throws Exception {
        DataSet ds = new DataSet();
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            double[] inputs = new double[parts.length - 1];

            for (int i = 0; i < parts.length - 1; i++)
                inputs[i] = Double.parseDouble(parts[i]);

            double target = Double.parseDouble(parts[parts.length - 1]);

            ds.rows.add(new DataRow(inputs, target));
        }

        br.close();

        ds.numVars = ds.rows.get(0).size();
        return ds;
    }

    public int size() {
        return rows.size();
    }

    public DataRow getRow(int i) {
        return rows.get(i);
    }

    public int getNumVars() {
        return numVars;
    }
}
