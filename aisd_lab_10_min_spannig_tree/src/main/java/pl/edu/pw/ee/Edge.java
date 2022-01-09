package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {
    private Node start;
    private Node end;
    private int cost;

    public Edge(Node start, Node end, int cost) {
        validateInput(start, end, cost);
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public int compareTo(Edge compared) {
        if (compared == null) {
            throw new IllegalArgumentException("Cannot compare Edge to null");
        }
        return Double.compare(compared.getCost(), this.cost);
    }

    @Override
    public String toString() {
        return start + "_" + cost + "_" + end;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge compared = (Edge) o;
        if (this.getCost() == compared.getCost()) {
            if (this.getStart().equals(compared.getStart()) && this.getEnd().equals(compared.getEnd())) {
                return true;
            }
            if (this.getStart().equals(compared.getEnd()) && this.getEnd().equals(compared.getStart())) {
                return true;
            }
        }
        return false;
    }

    private void validateInput(Node start, Node end, int cost) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The beginning and end of the edge cannot be null");
        }

        if (cost < 0) {
            throw new IllegalArgumentException("The edge weight cannot be less than 1");
        }
    }
}
