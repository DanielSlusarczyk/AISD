package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.edu.pw.ee.map.RbtMap;
import pl.edu.pw.ee.priorityQueue.Heap;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    private File dataFile;
    private RbtMap<Node, List<Edge>> graph;
    private RbtMap<String, Node> addedNodes;
    private String minSpanningTree;
    private String firstNodeLabel;
    private int numberOfVisitedNodes = 1;

    public String findMST(String pathToFile) {
        validateInput(pathToFile);
        graph = new RbtMap<>();
        addedNodes = new RbtMap<>();
        minSpanningTree = "";
        numberOfVisitedNodes = 1;
        readGraph();
        determineMST();
        checkConnectivity();

        return minSpanningTree.substring(0, minSpanningTree.length() - 1);
    }

    public void setFirstNode(String labelOfFirstNode) {
        if (labelOfFirstNode != null) {
            firstNodeLabel = labelOfFirstNode;
        }
    }

    private void determineMST() {
        Heap<Edge> currentQueue = new Heap<>();

        Node firstNode = null;
        if(firstNodeLabel != null){
            firstNode = addedNodes.getValue(firstNodeLabel);
        }
        if (firstNode == null) {
            firstNode = graph.getRoot();
        }
        System.out.println("Rozpoczynam od: " + firstNode);
        addNeighbors(currentQueue, firstNode);
        firstNode.setVisited();

        while (!currentQueue.isEmpty()) {
            Edge minEdge = currentQueue.pop();

            if (!minEdge.getEnd().isVisited()) {
                numberOfVisitedNodes ++;
                minEdge.getEnd().setVisited();
                System.out.println(minEdge);
                minSpanningTree = minSpanningTree + minEdge + "|";
                addNeighbors(currentQueue, minEdge.getEnd());
            }
        }
    }

    private void addNeighbors(Heap<Edge> heap, Node node) {
        List<Edge> listOfNeighbors = graph.getValue(node);
        for (Edge edge : listOfNeighbors) {
            if (!edge.getEnd().isVisited()) {
                heap.put(edge);
            }
        }
    }

    private void readGraph() {
        try {
            Scanner reader = new Scanner(dataFile);
            if(!reader.hasNextLine()){
                reader.close();
                throw new IllegalArgumentException("The input file is empty");
            }
            while (reader.hasNextLine()) {
                String actualString = reader.nextLine();
                if (actualString.matches("^[a-zA-Z]+ [a-zA-Z]+ \\d+$")) {
                    int firstSpaceIndex = actualString.indexOf(" ");
                    int secondSpaceIndex = actualString.lastIndexOf(" ");
                    String startNode = actualString.substring(0, firstSpaceIndex);
                    String endNode = actualString.substring(firstSpaceIndex + 1, secondSpaceIndex);
                    int cost = Integer.parseInt(actualString.substring(secondSpaceIndex + 1));
                    addEdge(startNode, endNode, cost);
                } else {
                    reader.close();
                    throw new IllegalArgumentException("Incorrect line [" + dataFile.getName() + "]: " + actualString);
                }
            }
            reader.close();

        } catch (NumberFormatException | FileNotFoundException exception) {
            throw new IllegalArgumentException("There is problem with input file");
        }
    }

    private void addEdge(String startString, String endString, int cost) {
        Node startNode = addedNodes.getValue(startString);
        Node endNode = addedNodes.getValue(endString);
        if (addedNodes.getValue(startString) == null) {
            startNode = new Node(startString);
            addedNodes.setValue(startString, startNode);
        }
        if (addedNodes.getValue(endString) == null) {
            endNode = new Node(endString);
            addedNodes.setValue(endString, endNode);
        }

        List<Edge> listOfEdgesForStartNode = graph.getValue(startNode);
        if (listOfEdgesForStartNode == null) {
            graph.setValue(startNode, new ArrayList<>());
        }
        graph.getValue(startNode).add(new Edge(startNode, endNode, cost));

        List<Edge> listOfEdgesForEndNode = graph.getValue(endNode);
        if (listOfEdgesForEndNode == null) {
            graph.setValue(endNode, new ArrayList<>());
        }
        graph.getValue(endNode).add(new Edge(endNode, startNode, cost));
    }

    private void checkConnectivity(){
        if(numberOfVisitedNodes != addedNodes.getSize()){
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
