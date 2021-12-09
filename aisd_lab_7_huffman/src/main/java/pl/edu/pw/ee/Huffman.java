package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Huffman {
    private BufferedReader reader;
    private BufferedWriter writer;
    private Node root;

    private String nameOfDecompressedFile = "decompressedFile.txt";
    private String nameOfCompressedFile = "compressedFile.txt";
    private String nameOfFileForKey = "key.txt";
    private File decompressedFile;
    private File compressedFile;
    private File keyFile;

    public int huffman(String pathToRootDir, boolean compress) {
        List<Node> listOfNodes = new ArrayList<>();
        try {
            validateInput(pathToRootDir, compress);
            if (compress) {
                readFrequencyOfSigns(listOfNodes);
                root = createHuffmanTree(listOfNodes);
                if (listOfNodes.size() > 1) {
                    codeValues(root, "");
                } else if (listOfNodes.size() == 1) {
                    listOfNodes.get(0).setCode("0");
                }
                saveHuffmanTree(listOfNodes);
                return codeFile(listOfNodes);

            } else {
                readHuffmanTree(listOfNodes);
                return decodeFile(listOfNodes);
            }

        } catch (IOException execption) {
            throw new IllegalArgumentException("There is problem with file");
        }
    }

    private void readFrequencyOfSigns(List<Node> listOfOccuredChars) throws IOException {
        reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(decompressedFile), StandardCharsets.UTF_8));
        String line = reader.readLine();
        String nextLine = reader.readLine();
        while (line != null) {
            if (nextLine != null) {
                line = line + '\n';
            }
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
            line = nextLine;
            nextLine = reader.readLine();
        }
        reader.close();
    }

    private Node createHuffmanTree(List<Node> listOfNodes) {
        if (listOfNodes.size() == 0) {
            return null;
        }
        if (listOfNodes.size() == 1) {
            return listOfNodes.get(0);
        }
        List<Node> listToMakeHuffmanTree = new ArrayList<>(listOfNodes);
        while (listToMakeHuffmanTree.size() > 1) {
            sortList(listToMakeHuffmanTree);
            Node first = listToMakeHuffmanTree.get(listToMakeHuffmanTree.size() - 1);
            Node second = listToMakeHuffmanTree.get(listToMakeHuffmanTree.size() - 2);
            listToMakeHuffmanTree.remove(listToMakeHuffmanTree.size() - 1);
            listToMakeHuffmanTree.remove(listToMakeHuffmanTree.size() - 1);
            listToMakeHuffmanTree.add(new Node(first.getFrequency() + second.getFrequency(), first, second));
        }
        return listToMakeHuffmanTree.get(0);
    }

    private void sortList(List<Node> listOfNodes) {
        listOfNodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.getFrequency() != o2.getFrequency()) {
                    return Integer.compare(o2.getFrequency(), o1.getFrequency());
                }
                if (!o1.isLeaf() && o2.isLeaf()) {
                    return 1;
                }
                if (o1.isLeaf() && !o2.isLeaf()) {
                    return -1;
                }
                if (o1.isLeaf() && o2.isLeaf()) {
                    return Character.compare(o1.getSign(), o1.getSign());
                }
                return -1;
            }

        });
    }

    private void sortListByLengthOfCode(List<Node> listOfNodes) {
        listOfNodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.getCode().length(), o2.getCode().length());
            }
        });
    }

    private void codeValues(Node node, String code) {
        if (node == null) {
            return;
        }
        if (node.getSign() != null) {
            node.setCode(code);
        }
        codeValues(node.getLeft(), code + "0");
        codeValues(node.getRight(), code + "1");
    }

    private int codeFile(List<Node> listOfCodes) throws IOException {
        reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(decompressedFile), StandardCharsets.UTF_8));
        writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(compressedFile), StandardCharsets.UTF_8));
        String encodedText = "";
        String line = reader.readLine();
        String nextLine = reader.readLine();
        int counter = 0;
        while (line != null) {
            if (nextLine != null) {
                System.out.print(line);
                line = line + '\n';
            }
            for (int i = 0; i < line.length(); i++) {
                char actualChar = line.charAt(i);
                for (Node node : listOfCodes) {
                    if (Character.compare(actualChar, node.getSign()) == 0) {
                        encodedText = encodedText + node.getCode();
                        break;
                    }
                }
            }
            writer.write(encodedText);
            if (counter >= Integer.MAX_VALUE - encodedText.length() - 1) {
                throw new IllegalArgumentException("There is too extensive file");
            }
            counter += encodedText.length();
            encodedText = "";
            line = nextLine;
            nextLine = reader.readLine() + '\n';
        }
        writer.close();
        reader.close();
        return counter;
    }

    private void saveHuffmanTree(List<Node> listOfNodes) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(keyFile), StandardCharsets.UTF_8));
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
            listOfNodes.add(new Node(actualChar, code));
        }
        scanner.close();
    }

    private int decodeFile(List<Node> listOfNodes) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(compressedFile), StandardCharsets.UTF_8));
        writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(decompressedFile), StandardCharsets.UTF_8));
        String line = reader.readLine();
        String encodedText = "";
        int counter = 0;
        sortListByLengthOfCode(listOfNodes);
        while (line != null) {
            line = line + '\n';
            String code = "";
            for (int i = 0; i < line.length(); i++) {
                for (Node node : listOfNodes) {
                    if (node.getCode().equals(code)) {
                        encodedText = encodedText + String.valueOf(node.getSign());
                        code = "";
                        if (counter >= Integer.MAX_VALUE - 1) {
                            throw new IllegalArgumentException("There is too extensive file");
                        }
                        counter++;
                        break;
                    }
                }
                if (code.length() > listOfNodes.get(listOfNodes.size() - 1).getCode().length()) {
                    throw new IllegalArgumentException("There is code without char in " + compressedFile.getName());
                }
                code = code + String.valueOf(line.charAt(i));
            }
            writer.write(encodedText);
            encodedText = "";
            line = reader.readLine();
        }

        writer.close();
        reader.close();
        return counter;
    }

    private void validateInput(String pathToRootDir, boolean compress) throws IOException {
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
            if (!decompressedFile.canRead()) {
                throw new IllegalArgumentException("Cannot read the file " + decompressedFile.getName());
            }
            if (!compressedFile.canWrite() || !keyFile.canWrite()) {
                compressedFile.createNewFile();
                keyFile.createNewFile();
            }
        } else {
            if (!compressedFile.canRead() || !keyFile.canRead()) {
                throw new IllegalArgumentException("Cannot write/read the file");
            }
            if (!decompressedFile.canWrite()) {
                decompressedFile.createNewFile();
            }
        }
    }

    public static void main(String[] argv) {
        System.out.println("Wynik: " + new Huffman().huffman("input", true));
    }
}
