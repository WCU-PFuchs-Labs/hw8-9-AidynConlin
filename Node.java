public class Node {

    public enum Type { VAR, CONST, OP }

    public enum OpType { ADD, SUB, MUL, DIV }

    public Type type;
    public int varIndex;
    public double constValue;
    public OpType op;
    public Node left, right;

    public Node(Type t) {
        type = t;
    }

    public static Node var(int index) {
        Node n = new Node(Type.VAR);
        n.varIndex = index;
        return n;
    }

    public static Node constant(double v) {
        Node n = new Node(Type.CONST);
        n.constValue = v;
        return n;
    }

    public static Node op(OpType o, Node l, Node r) {
        Node n = new Node(Type.OP);
        n.op = o;
        n.left = l;
        n.right = r;
        return n;
    }
}
