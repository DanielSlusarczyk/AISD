package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
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

    @Override
    public String findMST(String pathToFile) {
        validateInput(pathToFile);
        addedNodes = new RbtMap<>();
        minSpanningTree = "";
        priorityQueue = new PriorityQueue<>();
        readGraph();
        determineMST();
        checkConnectivity();

        return minSpanningTree.substring(0, minSpanningTree.length() == 0 ? 0 : minSpanningTree.length() - 1);
    }

    private void determineMST() {
        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.getMax();

            Node startNodeParent = addedNodes.getValue(minEdge.getStart().getLabel()).getRepresentative();
            Node endNodeParent = addedNodes.getValue(minEdge.getEnd().getLabel()).getRepresentative();

            if (!startNodeParent.equals(endNodeParent)) {
                startNodeParent.setParent(endNodeParent);
                nmbOfAddedEdges ++;
                minSpanningTree += minEdge + "|";
            }
        }
    }

    private void readGraph() {
        try {
            Scanner reader = new Scanner(dataFile);
            if (!reader.hasNextLine()) {
                reader.close();
                throw new IllegalArgumentException("The input file is empty");
            }
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

        } catch (IndexOutOfBoundsException | NumberFormatException | FileNotFoundException exception) {
            throw new IllegalArgumentException("There is problem with input file");
        }
    }

    private void addEdge(String startString, String endString, int cost) {
        if (startString.equals(endString)) {
            throw new IllegalArgumentException("The graph cannot have loops");
        }
        Node startNode = addedNodes.getValue(startString);
        Node endNode = addedNodes.getValue(endString);

        if (addedNodes.getValue(startString) == null) {
            startNode = new Node(startString);
            startNode.setParent(startNode);
            addedNodes.setValue(startString, startNode);
        }
        if (addedNodes.getValue(endString) == null) {
            endNode = new Node(endString);
            endNode.setParent(endNode);
            addedNodes.setValue(endString, endNode);
        }

        priorityQueue.insert(new Edge(startNode, endNode, cost));
    }

    private void checkConnectivity() {
        if (nmbOfAddedEdges != addedNodes.getSize() - 1) {
            throw new IllegalArgumentException("The graph is disconnected");
        }
    }

    private void validateInput(String pathToFile) {
        if (pathToFile == null) {
            throw new IllegalArgumentException("Path to the directory is null");
        }
        dataFile = new File(pathToFile);
        if (!dataFile.isFile()) {
            throw new IllegalArgumentException("Path does not lead to the file");
        }
        if (!dataFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from the directory");
        }
    }
}
