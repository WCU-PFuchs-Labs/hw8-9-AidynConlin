DataSet.java-import java.util.ArrayList;
import java.util.Iterator;

public class DataSet implements Iterable<DataRow> {

    private ArrayList<DataRow> rows = new ArrayList<>();

    public DataSet(String fileName) {
        // TODO: Load your CSV file here
        // For now, put dummy data so compilation succeeds:
        rows.add(new DataRow(0.0, 0.0));
        rows.add(new DataRow(1.0, 1.0));
    }

    @Override
    public Iterator<DataRow> iterator() {
        return rows.iterator();
    }
}