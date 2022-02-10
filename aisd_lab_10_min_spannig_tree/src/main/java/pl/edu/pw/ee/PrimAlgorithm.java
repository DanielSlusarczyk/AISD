package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.edu.pw.ee.map.RbtMap;
import pl.edu.pw.ee.priorityQueue.PriorityQueue;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    private File dataFile;
    private RbtMap<Node, List<Edge>> graph;
    private RbtMap<String, Node> addedNodes;
    private String minSpanningTree;
    private String firstNodeLabel;
    private int nmbOfAddedNodes = 1;

    public PrimAlgorithm() {
        graph = new RbtMap<>();
        addedNodes = new RbtMap<>();
        minSpanningTree = "";
    }

    public String findMST(String pathToFile) {
        validateInput(pathToFile);
        prepareVariables();
        readGraph();
        if (graph.getSize() > 0) {
            determineMST();
            checkConnectivity();
        }
        return minSpanningTree.substring(0, minSpanningTree.length() == 0 ? 0 : minSpanningTree.length() - 1);
    }

    public void setFirstNode(String labelOfFirstNode) {
        if (labelOfFirstNode != null) {
            this.firstNodeLabel = labelOfFirstNode;
        }
    }

    private void determineMST() {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        Node firstNode = null;
        if (firstNodeLabel != null) {
            firstNode = addedNodes.getValue(firstNodeLabel);
        }
        if (firstNode == null) {
            firstNode = graph.getRoot();
        }
        addNeighbors(priorityQueue, firstNode);
        firstNode.setVisited();

        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.getMax();

            if (!minEdge.getEnd().isVisited()) {
                nmbOfAddedNodes++;
                minEdge.getEnd().setVisited();
                minSpanningTree += minEdge + "|";
                addNeighbors(priorityQueue, minEdge.getEnd());
            }
        }
    }

    private void addNeighbors(PriorityQueue<Edge> queue, Node node) {
        List<Edge> listOfNeighbors = graph.getValue(node);

        for (int index = 0; index < listOfNeighbors.size(); index++) {
            if (!listOfNeighbors.get(index).getEnd().isVisited()) {
                queue.insert(listOfNeighbors.get(index));
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

        List<Edge> listOfEdgesForStartNode = graph.getValue(startNode);
        if (listOfEdgesForStartNode == null) {
            listOfEdgesForStartNode = new ArrayList<>();
            graph.setValue(startNode, listOfEdgesForStartNode);
        }
        listOfEdgesForStartNode.add(new Edge(startNode, endNode, cost));

        List<Edge> listOfEdgesForEndNode = graph.getValue(endNode);
        if (listOfEdgesForEndNode == null) {
            listOfEdgesForEndNode = new ArrayList<>();
            graph.setValue(endNode, listOfEdgesForEndNode);
        }
        listOfEdgesForEndNode.add(new Edge(endNode, startNode, cost));
    }

    private void checkConnectivity() {
        if (nmbOfAddedNodes != graph.getSize()) {
            throw new IllegalArgumentException("The graph is disconnected");
        }
    }

    private void prepareVariables() {
        if (addedNodes.getSize() != 0) {
            addedNodes = new RbtMap<>();
        }
        if (graph.getSize() != 0) {
            graph = new RbtMap<>();
        }
        if (minSpanningTree.length() != 0) {
            minSpanningTree = "";
        }
        nmbOfAddedNodes = 1;
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
