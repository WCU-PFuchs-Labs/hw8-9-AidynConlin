Node.java- public class Node implements Cloneable {

    private double value;     // placeholder field
    private Node left;
    private Node right;

    public Node() {
        this.value = 0.0;
        this.left = null;
        this.right = null;
    }

    public Node(double value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Node(Node other) {
        this.value = other.value;
        this.left = (other.left != null) ? new Node(other.left) : null;
        this.right = (other.right != null) ? new Node(other.right) : null;
    }

    @Override
    public Object clone() {
        return new Node(this);
    }

    public double eval(double x) {
        // placeholder eval function you can replace with real operators
        return value; 
    }
    @Override
public String toString() {

    return "(" + value + ")";
}


    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    public void setLeft(Node n) { left = n; }
    public void setRight(Node n) { right = n; }
}