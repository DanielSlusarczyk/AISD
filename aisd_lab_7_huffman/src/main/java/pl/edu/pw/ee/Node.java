package pl.edu.pw.ee;

public class Node {
    private Character sign;
    private int frequency;
    private Node leftNode;
    private Node rightNode;
    private String code;

    Node(Character sign, int frequency) {
        if (sign == null || frequency < 1) {
            throw new IllegalArgumentException("Sign cannot be null and frequnecy cannot be lower than one");
        }

        this.sign = sign;
        this.frequency = frequency;
        leftNode = null;
        rightNode = null;
    }

    Node(int frequency, Node left, Node right) {
        if (left == null || right == null || frequency < 1) {
            throw new IllegalArgumentException(
                    "Left and rigth node cannot be null and frequnecy cannot be lower than one");
        }

        this.sign = null;
        this.frequency = frequency;
        this.leftNode = left;
        this.rightNode = right;
    }

    Node(Character sign, String code) {
        if (code == null || sign == null) {
            throw new IllegalArgumentException("Code and sign cannot be null");
        }
        if (code.length() < 1) {
            throw new IllegalArgumentException("Code cannot be empty");
        }

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
        return "[" + sign + "->" + frequency + "->" + code + "]";
    }

}
