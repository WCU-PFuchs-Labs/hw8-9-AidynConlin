public class DataRow {

    private double[] inputs;
    private double target;

    public DataRow(double[] inputs, double target) {
        this.inputs = inputs;
        this.target = target;
    }

    public double get(int index) {
        return inputs[index];
    }

    public double getTarget() {
        return target;
    }

    public int size() {
        return inputs.length;
    }
}
