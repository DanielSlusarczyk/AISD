package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pl.edu.pw.ee.rbtMap.RbtMap;

public class Huffman {
    private RbtMap<Character, String> mapWithCodes;
    private RbtMap<Character, Node> mapOfFrequnecy;
    private RbtMap<String, Character> mapToDecode;
    private BufferedReader reader;
    private BufferedWriter writer;
    String decodedText = "";
    private Node root;

    String nameOfDecompressedFile = "decompressedFile.txt";
    String nameOfCompressedFile = "compressedFile.txt";
    String nameOfFileForKey = "key.txt";
    File decompressedFile;
    File compressedFile;
    File keyFile;

    Huffman() {
        mapOfFrequnecy = new RbtMap<>();
        mapWithCodes = new RbtMap<>();
        mapToDecode = new RbtMap<>();
    }

    public int huffman(String pathToRootDir, boolean compress) throws IOException {
        validateInput(pathToRootDir, compress);
        List<Node> listOfOccuredChars = new ArrayList<>();
        if (compress) {
            Heap<Node> heap = new Heap<>();
            readFrequencyOfSigns(listOfOccuredChars);
            addNodesToHeap(listOfOccuredChars, heap);
            root = createHuffmanTree(heap);
            codeValues(root, "");
            saveHuffmanTree(listOfOccuredChars);
            return codeFile(listOfOccuredChars);
        } else {
            readHuffmanTree(listOfOccuredChars);
            return decodeFile(listOfOccuredChars);
        }
    }

    private void readFrequencyOfSigns(List<Node> listOfOccuredChars) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(decompressedFile), StandardCharsets.UTF_8));
        String line = reader.readLine();
        while (line != null) {
            line = line + '\n';
            for (int i = 0; i < line.length(); i++) {
                char actualChar = line.charAt(i);
                Node properNode = null;
                for (Node node : listOfOccuredChars) {
                    if (Character.compare(node.getSign(), actualChar) == 0) {
                        properNode = node;
                        break;
                    }
                }
                if (properNode == null) {
                    listOfOccuredChars.add(new Node(actualChar, 1));
                } else {
                    properNode.increaseFrequency();
                }
            }
            line = reader.readLine();
        }
        reader.close();
    }

    private void addNodesToHeap(List<Node> listOfOccuredChars, Heap<Node> heap) {
        for (Node node : listOfOccuredChars) {
            heap.put(node);
        }
    }

    private Node createHuffmanTree(Heap<Node> heap) {
        while (heap.getSize() > 1) {
            Node first = heap.pop();
            Node second = heap.pop();
            heap.put(new Node(first.getFrequency() + second.getFrequency(), first, second));
        }
        return heap.pop();
    }

    private void codeValues(Node node, String code) {
        if (node == null) {
            return;
        }
        if (node.getSign() != null) {
            System.out.println(node.getSign() + "->" + code);
            node.setCode(code);
            // mapWithCodes.setValue(node.getSign(), code);
        }
        codeValues(node.getLeft(), code + "0");
        codeValues(node.getRight(), code + "1");
    }

    private int codeFile(List<Node> listOfNodes) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(decompressedFile), StandardCharsets.UTF_8));
        writer = new BufferedWriter(new FileWriter(compressedFile));
        String encodedText = "";
        String line = reader.readLine();
        while (line != null) {
            line = line + '\n';
            for (int i = 0; i < line.length(); i++) {
                char actualChar = line.charAt(i);
                for (Node node : listOfNodes) {
                    if (Character.compare(actualChar, node.getSign()) == 0) {
                        encodedText = encodedText + node.getCode();
                        break;
                    }
                }
            }
            line = reader.readLine();
        }
        writer.write(encodedText);
        writer.close();
        reader.close();
        return encodedText.length();
    }

    private void saveHuffmanTree(List<Node> listOfNodes) throws IOException {
        writer = new BufferedWriter(new FileWriter(keyFile));
        for (Node node : listOfNodes) {
            int decimalVersion = node.getSign();
            writer.write(decimalVersion + ":" + node.getCode() + " ");
        }
        writer.close();
    }

    private void readHuffmanTree(List<Node> listOfNodes) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(keyFile), StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNext("\\d+:\\d+")) {
            String node = scanner.next("\\d+:\\d+");
            char actualChar = (char) Integer.parseInt(node.substring(0, node.indexOf(':')));
            String code = node.substring(node.indexOf(':') + 1);
            // System.out.println("Kod: " + code + " Char: " + actualChar);
            listOfNodes.add(new Node(actualChar, code));
        }
        scanner.close();
    }

    private int decodeFile(List<Node> listOfNodes) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(compressedFile), StandardCharsets.UTF_8));
        writer = new BufferedWriter(new FileWriter(decompressedFile));
        String line = reader.readLine();
        String encodedText = "";
        int counter = 0;
        while (line != null) {
            line = line + '\n';
            String code = "";
            for (int i = 0; i < line.length(); i++) {
                for (Node node : listOfNodes) {
                    if (node.getCode().equals(code)) {
                        encodedText = encodedText + String.valueOf(node.getSign());
                        code = "";
                        counter++;
                        break;
                    }
                }
                code = code + String.valueOf(line.charAt(i));
            }
            line = reader.readLine();
        }

        writer.write(encodedText);
        writer.close();
        reader.close();
        return counter;
    }

    private void validateInput(String pathToRootDir, boolean compress) {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to dir is null");
        }
        File verifiedFile = new File(pathToRootDir);
        if (!verifiedFile.isDirectory()) {
            throw new IllegalArgumentException("Path does not lead to a directory");
        }
        if (!verifiedFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from directory");
        }

        decompressedFile = new File(verifiedFile.getPath() + "\\" + nameOfDecompressedFile);
        compressedFile = new File(verifiedFile.getPath() + "\\" + nameOfCompressedFile);
        keyFile = new File(verifiedFile.getPath() + "\\" + nameOfFileForKey);

        if (compress) {
            if (!decompressedFile.canRead() || !compressedFile.canWrite() || !keyFile.canWrite()) {
                throw new IllegalArgumentException("Cannot write/read the file");
            }
        } else {
            if (!decompressedFile.canWrite() || !compressedFile.canRead() || !keyFile.canRead()) {
                throw new IllegalArgumentException("Cannot write/read the file");
            }
        }
    }

    public static void main(String[] argv) {
        try {
            System.out.println("Wynik: " + new Huffman().huffman("input", false));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
