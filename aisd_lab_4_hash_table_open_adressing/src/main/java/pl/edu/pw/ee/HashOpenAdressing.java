package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private int size;
    private int nElems;
    private Element<T>[] hashElems;
    private final double correctLoadFactor;

    private class Element<T1>{
        private T1 element;
        boolean deleted = false;

        Element(T1 element){
            this.element = element;
        }
        Element(boolean deleted){
            element = null;
            deleted = true;
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

        while (hashElems[hashId] != null && hashElems[hashId].element.compareTo(newElem)!=0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
        if (hashElems[hashId] == null) {
            nElems++;
        }
        //System.out.println("Na pozycji: " + hashId + " Element: " + newElem);
        hashElems[hashId] = new Element<T>(newElem);
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != null) {
            if (hashElems[hashId].element!=null && hashElems[hashId].element.compareTo(elem) == 0) {
                return hashElems[hashId].element;
            }
            i = (i + 1) % size;
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
            if (!hashElems[hashId].deleted && hashElems[hashId].element != null && hashElems[hashId].element.compareTo(elem)==0) {
                hashElems[hashId] = new Element<>(true);
                nElems--;
                break;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
    }


    abstract public int getNumOfElems();

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

    double getCorrectLoadFactor(){
        return correctLoadFactor;
    }

    void print(){
        for(int i =0; i < hashElems.length; i++){
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
        Element<T>[] tmp = new Element[size];
        System.arraycopy(hashElems, 0, tmp, 0, size / 2);
        hashElems = tmp;
    }
}