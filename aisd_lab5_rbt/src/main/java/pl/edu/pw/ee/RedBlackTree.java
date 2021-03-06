package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private String traversalResult = "";
    private int nmbOfRecursiveCalls = 0;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        validateParams(key, value);
        nmbOfRecursiveCalls = 0;
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    public String getInOrder() {
        traversalResult = "";
        returnInOrder(root);
        return traversalResult.trim();
    }

    private void returnInOrder(Node<K, V> node) {
        if (node != null) {
            returnInOrder(node.getLeft());
            traversalResult = traversalResult + node.getKey() + ":" + node.getValue() + " ";
            returnInOrder(node.getRight());
        }
    }

    public String getPreOrder() {
        traversalResult = "";
        returnPreOrder(root);
        return traversalResult.trim();
    }

    private void returnPreOrder(Node<K, V> node) {
        if (node != null) {
            traversalResult = traversalResult + node.getKey() + ":" + node.getValue() + " ";
            returnPreOrder(node.getLeft());
            returnPreOrder(node.getRight());
        }
    }

    public String getPostOrder() {
        traversalResult = "";
        returnPostOrder(root);
        return traversalResult.trim();
    }

    private void returnPostOrder(Node<K, V> node) {
        if (node != null) {
            returnPostOrder(node.getLeft());
            returnPostOrder(node.getRight());
            traversalResult = traversalResult + node.getKey() + ":" + node.getValue() + " ";
        }
    }

    public int getNumberOfCalls() {
        return nmbOfRecursiveCalls;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        nmbOfRecursiveCalls++;
        if (node == null) {
            return new Node<>(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null
                ? false
                : node.isRed();
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (node != null) {
            if (isRed(node.getLeft())) {
                node = rotateRight(node);
            }

            if (node.getRight() == null) {
                return null;
            }

            if (!isRed(node.getRight()) && !isRed(node.getRight().getLeft())) {
                swapColor(node);
                swapColor(node.getLeft());
                swapColor(node.getRight());
                if (isRed(node.getLeft().getLeft())) {
                    node = rotateRight(node);
                    swapColor(node);
                    swapColor(node.getLeft());
                    swapColor(node.getRight());
                }
            }

            node.setRight(deleteMax(node.getRight()));
            return reorganizeTree(node);
        }
        return node;
    }

    private void swapColor(Node<K, V> node) {
        if (node != null) {
            if (isRed(node)) {
                node.setColor(BLACK);
            } else {
                node.setColor(RED);
            }
        }
    }
}
