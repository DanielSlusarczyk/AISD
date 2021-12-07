package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.edu.pw.ee.rbtMap.RbtMap;

public class Huffman {
    private Heap<Node> heap;
    private int[] frequency;
    private RbtMap<Character, String> rbtMap;
    private BufferedReader reader;
    private BufferedWriter writer;
    private File file;
    private List<String> text;
    private String traversalResult ="";
    private Node root;

    Huffman() {
        heap = new Heap<>();
        frequency = new int[256];
        rbtMap = new RbtMap<>();
        text = new ArrayList<>();
    }

    public int huffman(String pathToFile, boolean compress) throws IOException {
        if (compress) {
            readFrequencyOfSigns(pathToFile);
            addNodesToHeap();
            createHuffmanTree();
            root = heap.pop();
            codeValues(root, "");
            saveHuffmanTree(pathToFile);
            System.out.println(codeFile(pathToFile));
            return 0;
        } else {
            readHuffmanTree(pathToFile);
            decodeFile(pathToFile);
        }

        return -1;
    }

    private void readFrequencyOfSigns(String filePath) throws IOException {
        file = new File(filePath);
        reader = new BufferedReader(new FileReader(file));
        String line = "";
        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) < frequency.length) {
                    ++frequency[line.charAt(i)];
                }
            }
            //++frequency['\n'];
            text.add(line);
            line = reader.readLine();
        }
        reader.close();
    }

    private void addNodesToHeap() {
        for (char i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                heap.put(new Node(i, frequency[i]));
            }
        }
    }

    private void createHuffmanTree() {
        while (heap.getSize() > 1) {
            Node first = heap.pop();
            Node second = heap.pop();
            heap.put(new Node(first.getFrequency() + second.getFrequency(), first, second));
        }
    }

    private void codeValues(Node node, String code) {
        if (node == null) {
            return;
        }
        if (node.getSign() != null) {
            System.out.println(node.getSign() + "->" + code);
            rbtMap.setValue(node.getSign(), code);
        }
        codeValues(node.getLeft(), code + "0");
        codeValues(node.getRight(), code + "1");
    }

    private int codeFile(String filePath) throws IOException {
        String codedValue = "";
        for (String line : text) {
            for (int i = 0; i < line.length(); i++) {
                codedValue += rbtMap.getValue(line.charAt(i));
            }
        }

        writer = new BufferedWriter(new FileWriter(file));
        writer.write(codedValue);
        writer.close();
        return codedValue.length();
    }

    private void saveHuffmanTree(String pathFile) throws IOException {
        File auxFile = new File(pathFile);
        File treeFile = new File(auxFile.getParent() + "\\tree_" + auxFile.getName());
        writer = new BufferedWriter(new FileWriter(treeFile));
        traverseTree(root);
        writer.write(traversalResult);
        writer.close();
    }

    private void traverseTree(Node node){
        if (node != null) {
            if(node.isLeaf()){
                traversalResult = traversalResult + node.getSign() + ":" + node.getFrequency() + " ";
            }
            else {
                traversalResult = traversalResult + "()" + ":" + node.getFrequency() + " ";
            }
            traverseTree(node.getLeft());
            traverseTree(node.getRight());
        }
        else {
            traversalResult = traversalResult + "#:# ";
        }
    }

    private void readHuffmanTree(String pathFile) throws IOException {
        File auxFile = new File(pathFile);
        File treeFile = new File(auxFile.getParent() + "\\tree_" + auxFile.getName());
        reader = new BufferedReader(new FileReader(treeFile));
        //String lineTree = reader.readLine();
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext("\\p{ASCII}:\\d+|#:#|\\(\\):\\d+")){
            System.out.println(scanner.next("\\p{ASCII}:\\d+|#:#|\\(\\):\\d+"));
        }
        //buildTreeFromFile(lineTree, 0);
        scanner.close();
        //reader.close();
    }

    private void buildTreeFromFile(String lineTree, int charIndex) throws FileNotFoundException{
        if(charIndex > lineTree.length()){
            return;
        }
        char actualChar = lineTree.charAt(charIndex);

    }

    private void decodeFile(String pathFile){

    }

    public static void main(String[] argv) {
        try {
            new Huffman().huffman("src/main/java/pl/edu/pw/ee/niemanie_refren.txt", true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
