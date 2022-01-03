package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Node implements Comparable<Node> {
    String label;
    boolean visited;
    List<Node> list;

    Node(String label) {
        validateInput(label);
        this.label = label;
        this.visited = false;
        this.list = new ArrayList<>();
    }

    public void addToList(Node node){
        if(node == null){
            throw new IllegalArgumentException("Node is null");
        }
        list.add(node);
    }

    public boolean check(Node node){
        if(node == null){
            throw new IllegalArgumentException("Node is null");
        }
        return list.contains(node);
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
