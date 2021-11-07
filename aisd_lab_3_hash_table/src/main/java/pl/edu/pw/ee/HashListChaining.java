package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private List<Elem> hashElems;
    private int nElem;
    private int size;

    private class Elem {
        private T value;
        private Elem next;

        Elem(T value, Elem nextElem) {
            this.value = value;
            this.next = nextElem;
        }

        public void setNext(Elem nextElem) {
            this.next = nextElem;
        }
    }

    public HashListChaining(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Cannot set size to: " + size);
        }

        hashElems = new ArrayList<>();
        this.size = size;
        initializeHash();
    }

    @Override
    public void add(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        if (hashElems.get(hashId) == nil) {
            hashElems.set(hashId, new Elem(value, nil));
            nElem++;
        } else {
            Elem oldElem = hashElems.get(hashId);
            while (oldElem.next != nil && !oldElem.value.equals(value)) {
                oldElem = oldElem.next;
            }
            if (oldElem.value.equals(value)) {
                oldElem.value = value;
            } else {
                oldElem.setNext(new Elem(value, nil));
                nElem++;
            }
        }
    }

    @Override
    public T get(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot get a null element");
        }

        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);
        Elem elem = hashElems.get(hashId);

        while (elem != nil && !elem.value.equals(value)) {
            elem = elem.next;
        }

        return elem != nil ? elem.value : null;
    }

    @Override
    public void delete(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems.get(hashId);
        if (elem != nil && elem.value.equals(value)) {
            hashElems.set(hashId, elem.next);
            nElem--;
        }
        if (elem != nil) {
            while (elem.next != nil && !elem.next.value.equals(value)) {
                elem = elem.next;
            }
            if (elem.next != nil) {
                elem.setNext(elem.next.next);
                nElem--;
            }
        }
    }

    public double countLoadFactor() {
        return Double.valueOf(nElem) / size;
    }

    public int getNumberOfElements() {
        return nElem;
    }

    private void initializeHash() {
        for (int i = 0; i < size; i++) {
            hashElems.add(i, nil);
        }
    }

    private int countHashId(int hashCode) {
        return Math.abs(hashCode) % size;
    }
}