package pl.edu.pw.ee;

public class Node implements Comparable<Node> {
    String label;
    boolean visited;

    Node(String label) {
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
}
