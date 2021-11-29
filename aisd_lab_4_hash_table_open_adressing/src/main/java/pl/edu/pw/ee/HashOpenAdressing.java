package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private int size;
    private int nElems;
    private Element<T>[] hashElems;
    private final double correctLoadFactor;

    private class Element<T1 extends Comparable<T1>>{
        private T1 element;
        private boolean deleted = false;

        Element(T1 element) {
            this.element = element;
        }

        void setAsDeleted() {
            deleted = true;
        }

        boolean isDeleted() {
            return deleted;
        }

        boolean isEqueal(T1 elem) {
            return element.compareTo(elem) == 0;
        }
    }

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    @SuppressWarnings("unchecked")
    HashOpenAdressing(int size) {
        validateHashInitSize(size);
        this.size = size;
        this.hashElems = new Element[this.size];
        this.correctLoadFactor = 0.75;
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != null) {
            if (hashElems[hashId].isDeleted() || hashElems[hashId].isEqueal(newElem)) {
                break;
            }
            i = (i + 1);
            if (i > size) {
                System.out.println("Zapętlenie");
                doubleResize();
                i = 0;
            }
            hashId = hashFunc(key, i);
        }
        if (hashElems[hashId] == null) {
            nElems++;
        }
        hashElems[hashId] = new Element<T>(newElem);
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != null) {
            if (!hashElems[hashId].isDeleted() && hashElems[hashId].isEqueal(elem)) {
                return hashElems[hashId].element;
            }
            i = i + 1;
            if( i > size){
                break;
            }
            hashId = hashFunc(key, i);
        }
        return null;
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != null) {
            if (!hashElems[hashId].isDeleted() && hashElems[hashId].isEqueal(elem)) {
                hashElems[hashId].setAsDeleted();
                nElems--;
                break;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i);

    int getSize() {
        return size;
    }

    int getNElem() {
        return nElems;
    }

    double getCorrectLoadFactor() {
        return correctLoadFactor;
    }

    // TODO: Usunąć metodę
    void print() {
        for (int i = 0; i < hashElems.length; i++) {
            System.out.println("[" + i + "]" + hashElems[i]);
        }
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            doubleResize();
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    @SuppressWarnings("unchecked")
    private void doubleResize() {
        this.size *= 2;
        Element<T>[] src = hashElems;
        hashElems = new Element[this.size];
        nElems = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] != null && !src[i].isDeleted()) {
                put(src[i].element);
            }
        }
    }
}