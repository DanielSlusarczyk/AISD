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
            throw new IllegalArgumentException("Cannot compare the edge to null");
        }
        int result = Double.compare(compared.getCost(), this.cost);
        if (result == 0) {
            if (this.getEnd().compareTo(compared.getEnd()) == 0
                    && this.getStart().compareTo(compared.getStart()) == 0) {
                result = 0;
            } else if (this.getEnd().compareTo(compared.getStart()) == 0
                    && this.getStart().compareTo(compared.getEnd()) == 0) {
                result = 0;
            } else {
                result = compared.getEnd().getLabel().compareTo(this.getEnd().getLabel());
                if (result == 0) {
                    result = compared.getStart().getLabel().compareTo(this.getStart().getLabel());
                }
            }
        }
        return result;

    }

    @Override
    public String toString() {
        return start + "_" + cost + "_" + end;
    }

    private void validateInput(Node start, Node end, int cost) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("The beginning and end of the edge cannot be null");
        }

        if (cost < 0) {
            throw new IllegalArgumentException("The weight of the edge cannot be less than 1");
        }
    }
}
