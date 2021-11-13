package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private final Object deleted = null;
    private int size;
    private int nElems;
    private T[] hashElems;
    private final double correctLoadFactor;

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    @SuppressWarnings("unchecked")
    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.correctLoadFactor = 0.75;
    }

    void comp(){
        if(nil == deleted){
            System.out.println("Nołpa lumpa");
        }
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil && hashElems[hashId].compareTo(newElem)!=0) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
        if (hashElems[hashId] == nil) {
            nElems++;
        }
        //System.out.println("Na pozycji: " + hashId + " Element: " + newElem);
        hashElems[hashId] = newElem;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);

        int key = elem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i);

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].compareTo(elem) == 0) {
                return hashElems[hashId];
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

        while (hashElems[hashId] != nil) {
            if (hashElems[hashId].compareTo(elem)==0) {
                orderArray(key, hashId, i);
                nElems--;
                break;
            }
            i = (i + 1) % size;
            hashId = hashFunc(key, i);
        }
    }


    abstract public int getNumOfElems();

    private void orderArray(int key, int hashId, int i) {
        int iterator = (i + 1) % size;
        int nextHashId = hashFunc(key, iterator);
        while (hashElems[nextHashId] != nil) {
            hashElems[hashId] = hashElems[nextHashId];
            hashId = nextHashId;
            iterator = (iterator + 1) % size;
            nextHashId = hashFunc(key, iterator);
        }
        hashElems[hashId] = nil;
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
        T[] tmp = (T[]) new Comparable[size];
        System.arraycopy(hashElems, 0, tmp, 0, size / 2);
        hashElems = tmp;
    }
}