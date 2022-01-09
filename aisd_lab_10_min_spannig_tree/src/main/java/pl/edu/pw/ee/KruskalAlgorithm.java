package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import pl.edu.pw.ee.map.RbtMap;
import pl.edu.pw.ee.priorityQueue.PriorityQueue;
import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {
    private File dataFile;
    private RbtMap<String, Node> addedNodes;
    private PriorityQueue<Edge> priorityQueue;
    private String minSpanningTree;
    private int nmbOfAddedEdges = 0;

    public KruskalAlgorithm() {
        addedNodes = new RbtMap<>();
        minSpanningTree = "";
    }

    @Override
    public String findMST(String pathToFile) {
        validateInput(pathToFile);
        prepareVariables();
        readGraph();
        if (priorityQueue.getSize() > 0) {
            determineMST();
            checkConnectivity();
        }

        return minSpanningTree.substring(0, minSpanningTree.length() == 0 ? 0 : minSpanningTree.length() - 1);
    }

    private void determineMST() {
        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.getMax();

            Node startNodeParent = minEdge.getStart().getRepresentative();
            Node endNodeParent = minEdge.getEnd().getRepresentative();

            if (startNodeParent.compareTo(endNodeParent) != 0) {
                startNodeParent.setParent(endNodeParent);
                nmbOfAddedEdges++;
                minSpanningTree += minEdge + "|";
            }
        }
    }

    private void readGraph() {
        try {
            Scanner reader = new Scanner(dataFile);

            while (reader.hasNextLine()) {
                String actualLine = reader.nextLine();
                if (actualLine.matches("^[a-zA-Z]+ [a-zA-Z]+ \\d+$")) {
                    int firstSpaceIndex = actualLine.indexOf(" ");
                    int secondSpaceIndex = actualLine.lastIndexOf(" ");

                    String startNode = actualLine.substring(0, firstSpaceIndex);
                    String endNode = actualLine.substring(firstSpaceIndex + 1, secondSpaceIndex);
                    int cost = Integer.parseInt(actualLine.substring(secondSpaceIndex + 1));
                    addEdge(startNode, endNode, cost);
                } else {
                    reader.close();
                    throw new IllegalArgumentException("Incorrect line [" + dataFile.getName() + "]: " + actualLine);
                }
            }
            reader.close();
        } catch (IndexOutOfBoundsException | NumberFormatException | IOException exception) {
            throw new IllegalArgumentException("There is problem with input file");
        }
    }

    private void addEdge(String startString, String endString, int cost) {
        if (startString.equals(endString)) {
            throw new IllegalArgumentException("The graph cannot have loops");
        }
        Node startNode = addedNodes.getValue(startString);
        Node endNode = addedNodes.getValue(endString);

        if (startNode == null) {
            startNode = new Node(startString);
            addedNodes.setValue(startString, startNode);
        }
        if (endNode == null) {
            endNode = new Node(endString);
            addedNodes.setValue(endString, endNode);
        }

        priorityQueue.insert(new Edge(startNode, endNode, cost));
    }

    private void checkConnectivity() {
        if (nmbOfAddedEdges != addedNodes.getSize() - 1) {
            throw new IllegalArgumentException("The graph is disconnected");
        }
    }

    private void prepareVariables() {
        if (addedNodes.getSize() != 0) {
            addedNodes = new RbtMap<>();
        }
        if (minSpanningTree.length() != 0) {
            minSpanningTree = "";
        }
        priorityQueue = new PriorityQueue<>();
        nmbOfAddedEdges = 0;
    }

    private void validateInput(String pathToFile) {
        if (pathToFile == null) {
            throw new IllegalArgumentException("Path to the file is null");
        }
        dataFile = new File(pathToFile);
        if (!dataFile.isFile()) {
            throw new IllegalArgumentException("Path does not lead to the file");
        }
    }
}
