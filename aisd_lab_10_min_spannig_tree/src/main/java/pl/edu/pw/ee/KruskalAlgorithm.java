package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.edu.pw.ee.map.RbtMap;
import pl.edu.pw.ee.priorityQueue.Heap;
import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {
    private File dataFile;
    private RbtMap<String, Node> addedNodes;
    private Heap<Edge> priorityQueue;
    private List<Node> listOfNodes;
    private String minSpanningTree;

    @Override
    public String findMST(String pathToFile) {
        validateInput(pathToFile);
        addedNodes = new RbtMap<>();
        priorityQueue = new Heap<>();
        listOfNodes = new ArrayList<>();
        minSpanningTree = "";
        readGraph();
        determineMST();

        return minSpanningTree.substring(0, minSpanningTree.length() == 0 ? 0 : minSpanningTree.length() - 1);
    }

    private void determineMST() {
        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.pop();
            System.out.println("Sprawdzam: " + minEdge);

            Node startNode = addedNodes.getValue(minEdge.getStart().getLabel());
            Node endNode = addedNodes.getValue(minEdge.getEnd().getLabel());

            if(!startNode.check(endNode) && !endNode.check(startNode)){
                startNode.addToList(endNode);
                startNode.addToList(startNode);
                endNode.setList(startNode.getNodes());
            }
            startNode.printList();
            endNode.printList();
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
            addedNodes.setValue(startString, startNode);
            listOfNodes.add(startNode);
        }
        if (addedNodes.getValue(endString) == null) {
            endNode = new Node(endString);
            addedNodes.setValue(endString, endNode);
            listOfNodes.add(endNode);
        }

        priorityQueue.put(new Edge(startNode, endNode, cost));
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
