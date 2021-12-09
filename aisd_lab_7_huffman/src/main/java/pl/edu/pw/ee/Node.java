package pl.edu.pw.ee;

public class Node {
    private Character sign;
    private int frequency;
    private Node leftNode;
    private Node rightNode;
    private String code;

    Node(char sign, int frequnecy) {
        this.sign = sign;
        this.frequency = frequnecy;
        leftNode = null;
        rightNode = null;
    }

    Node(int frequency, Node left, Node right) {
        this.sign = null;
        this.frequency = frequency;
        this.leftNode = left;
        this.rightNode = right;
    }

    Node(char sign, String code) {
        this.sign = sign;
        this.code = code;
        this.leftNode = null;
        this.rightNode = null;
    }

    public void increaseFrequency() {
        frequency++;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Character getSign() {
        return sign;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean isLeaf() {
        return leftNode == null && rightNode == null;
    }

    public Node getLeft() {
        return leftNode;
    }

    public Node getRight() {
        return rightNode;
    }

    @Override
    public boolean equals(Object node) {
        if (!(node instanceof Node) || node == null) {
            return false;
        }
        Node comparedNode = (Node) node;
        if (this.isLeaf() && comparedNode.isLeaf()) {
            return Character.compare(this.sign, comparedNode.getSign()) == 0;
        } else {
            return Integer.compare(comparedNode.getFrequency(), this.getFrequency()) == 0;
        }
    }

    @Override
    public String toString() {
        return "[" + sign + "->" + frequency + "->" + code +"]";
    }

}
