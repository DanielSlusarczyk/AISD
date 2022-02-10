package pl.edu.pw.ee;

public class Node implements Comparable<Node> {
    String label;
    boolean visited;
    Node parent;

    public Node(String label) {
        validateInput(label);
        this.label = label;
        this.visited = false;
        this.parent = this;
    }

    public void setVisited() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public String getLabel() {
        return label;
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public Node getRepresentative() {
        if (this.compareTo(parent) == 0) {
            return this;
        }
        return parent.getRepresentative();
    }

    @Override
    public int compareTo(Node compared) {
        if (compared == null) {
            throw new IllegalArgumentException("Cannot compare Node to null");
        }
        return label.compareTo(compared.getLabel());
    }

    @Override
    public String toString() {
        return label;
    }

    private void validateInput(String label) {
        if (label == null) {
            throw new IllegalArgumentException("The label of node cannot be null");
        }
        if (label.length() == 0) {
            throw new IllegalArgumentException("The label of node cannot be empty");
        }
    }
}
