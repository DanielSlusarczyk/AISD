package pl.edu.pw.ee.map;

import pl.edu.pw.ee.map.services.MapInterface;

public class RbtMap<K extends Comparable<K>, V> implements MapInterface<K, V> {
    private final RedBlackTree<K, V> tree;

    public RbtMap() {
        tree = new RedBlackTree<>();
    }

    @Override
    public void setValue(K key, V value) {
        tree.put(key, value);
    }

    @Override
    public V getValue(K key) {
        return tree.get(key);
    }

    public K getRoot() {
        return tree.getRoot();
    }

    public int getSize() {
        return tree.getSize();
    }
}
