package pl.edu.pw.ee;

public class Node implements Comparable<Node> {
    String label;
    boolean visited;

    Node(String label) {
        validateInput(label);
        this.label = label;
        this.visited = false;
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node compared = (Node) o;
        return label.compareTo(compared.getLabel()) == 0;
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
