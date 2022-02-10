package pl.edu.pw.ee;

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

public class Huffman {
    private InputStreamReader reader;
    private BufferedWriter writer;
    private Node root;
    private String nameOfDecompressedFile = "decompressedFile.txt";
    private String nameOfCompressedFile = "compressedFile.txt";
    private String nameOfFileForKey = "key.txt";
    private String traversalResult = "";
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
                    sortListByLengthOfCode(listOfNodes);
                } else if (listOfNodes.size() == 1) {
                    listOfNodes.get(0).setCode("0");
                }

                saveHuffmanTree(listOfNodes);
                return codeFile(listOfNodes);
            } else {
                root = readHuffmanTree(listOfNodes);

                if (listOfNodes.size() > 1) {
                    codeValues(root, "");
                    sortListByLengthOfCode(listOfNodes);
                } else if (listOfNodes.size() == 1) {
                    listOfNodes.get(0).setCode("0");
                }

                return decodeFile(listOfNodes);
            }
        } catch (IOException execption) {
            throw new IllegalArgumentException("There is problem with files");
        }
    }

    private void readFrequencyOfSigns(List<Node> listOfOccuredChars) throws IOException {
        reader = new InputStreamReader(new FileInputStream(decompressedFile), StandardCharsets.UTF_8);
        int singleChar = reader.read();
        while (singleChar != -1) {
            char actualChar = (char) singleChar;
            Node properNode = null;

            for (Node node : listOfOccuredChars) {
                if (Character.compare(node.getSign(), actualChar) == 0) {
                    properNode = node;
                    break;
                }
            }

            if (properNode == null) {
                Node newNode = new Node(actualChar, 1);
                listOfOccuredChars.add(newNode);
            } else {
                properNode.increaseFrequency();
            }

            singleChar = reader.read();
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

        Heap<Node> heap = new Heap<>();
        for (Node node : listOfNodes) {
            heap.put(node);
        }

        while (heap.getSize() > 1) {
            Node firstMin = heap.pop();
            Node secondMin = heap.pop();
            heap.put(new Node(firstMin.getFrequency() + secondMin.getFrequency(), firstMin, secondMin));
        }
        return heap.pop();
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
        reader = new InputStreamReader(new FileInputStream(decompressedFile), StandardCharsets.UTF_8);
        writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(compressedFile), StandardCharsets.UTF_8));
        int singleChar = reader.read();
        int counter = 0;
        while (singleChar != -1) {
            char actualChar = (char) singleChar;
            for (Node node : listOfCodes) {
                if (Character.compare(actualChar, node.getSign()) == 0) {
                    counter += node.getCode().length();
                    writer.write(node.getCode());
                    break;
                }
            }
            singleChar = reader.read();
        }
        writer.close();
        reader.close();
        return counter;
    }

    private int decodeFile(List<Node> listOfCodes) throws IOException {
        reader = new InputStreamReader(new FileInputStream(compressedFile), StandardCharsets.UTF_8);
        writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(decompressedFile), StandardCharsets.UTF_8));
        int singleChar = reader.read();
        int counter = 0;
        String code = "";
        while (singleChar != -1) {
            code = code + (char) singleChar;
            for (Node node : listOfCodes) {
                if (node.getCode().equals(code)) {
                    writer.write(String.valueOf(node.getSign()));
                    code = "";

                    if (counter >= Integer.MAX_VALUE - 1) {
                        writer.close();
                        reader.close();
                        throw new IllegalArgumentException("There is too extensive file");
                    }
                    counter++;
                    break;
                }
            }
            if (code.length() > listOfCodes.get(listOfCodes.size() - 1).getCode().length()) {
                writer.close();
                reader.close();
                throw new IllegalArgumentException("There is code without char in " + compressedFile.getName());
            }
            singleChar = reader.read();
        }
        writer.close();
        reader.close();
        return counter;
    }

    private void saveHuffmanTree(List<Node> listOfNodes) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(keyFile), StandardCharsets.UTF_8));
        traverseHuffmanTree(root);
        writer.write(traversalResult);
        writer.close();
    }

    private void traverseHuffmanTree(Node node) throws IOException {
        if (node != null) {
            if (node.isLeaf()) {
                traversalResult += "1" + node.getSign();
            } else {
                traversalResult += "0";
            }
            traverseHuffmanTree(node.getLeft());
            traverseHuffmanTree(node.getRight());
        }
    }

    private Node readHuffmanTree(List<Node> listOfNodes) throws IOException {
        reader = new InputStreamReader(new FileInputStream(keyFile), StandardCharsets.UTF_8);
        int singleChar = reader.read();
        List<Character> tree = new ArrayList<>();

        while (singleChar != -1) {
            tree.add((char) singleChar);
            singleChar = reader.read();
        }

        root = buildTree(null, tree, listOfNodes);
        reader.close();
        return root;
    }

    private Node buildTree(Node actualNode, List<Character> tree, List<Node> listOfNodes) {
        if (tree.size() > 0) {
            char actualChar = tree.get(0);
            tree.remove(0);

            if (Character.compare(actualChar, '0') == 0) {
                Node node = new Node();
                node.setLeft(buildTree(node, tree, listOfNodes));
                node.setRight(buildTree(node, tree, listOfNodes));
                return node;
            } else if (Character.compare(actualChar, '1') == 0) {
                char sign = tree.get(0);
                tree.remove(0);
                Node node = new Node(sign, 1);
                listOfNodes.add(node);
                return node;
            } else {
                throw new IllegalArgumentException("There is not allowed sign in file with key");
            }
        }
        return actualNode;
    }

    private void validateInput(String pathToRootDir, boolean compress) throws IOException {
        if (pathToRootDir == null) {
            throw new IllegalArgumentException("Path to the directory is null");
        }
        File verifiedFile = new File(pathToRootDir);
        if (!verifiedFile.isDirectory()) {
            throw new IllegalArgumentException("Path does not lead to the directory");
        }
        if (!verifiedFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from the directory");
        }

        decompressedFile = new File(verifiedFile.getPath() + "\\" + nameOfDecompressedFile);
        compressedFile = new File(verifiedFile.getPath() + "\\" + nameOfCompressedFile);
        keyFile = new File(verifiedFile.getPath() + "\\" + nameOfFileForKey);

        if (compress) {
            validateInputForCompress();
        } else {
            valideInputForDecompress();
        }
    }

    private void validateInputForCompress() throws IOException {
        if (!decompressedFile.exists()) {
            throw new IllegalArgumentException("Cannot compress without decompressed file");
        }
        if (!decompressedFile.canRead()) {
            throw new IllegalArgumentException("Cannot read the file " + decompressedFile.getName());
        }
        if (!compressedFile.exists() || !keyFile.exists()) {
            if (!compressedFile.createNewFile() || !keyFile.createNewFile()) {
                throw new IllegalArgumentException("Cannot create required files");
            }
        }
        if (!compressedFile.canWrite() || !keyFile.canWrite()) {
            throw new IllegalArgumentException("Cannot write to files while compress");
        }
    }

    private void valideInputForDecompress() throws IOException {
        if (!compressedFile.exists() || !keyFile.exists()) {
            throw new IllegalArgumentException("Cannot decompress without compressed file or key");
        }
        if (!compressedFile.canRead() || !keyFile.canRead()) {
            throw new IllegalArgumentException("Cannot read from the files while decompress");
        }
        if (!decompressedFile.exists()) {
            if (!decompressedFile.createNewFile()) {
                throw new IllegalArgumentException("Cannot create required file");
            }
        }
        if (!decompressedFile.canWrite()) {
            throw new IllegalArgumentException("Cannot write to file while compress");
        }
    }
}
