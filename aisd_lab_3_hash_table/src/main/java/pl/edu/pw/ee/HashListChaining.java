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
        hashElems = new ArrayList<>();
        this.size = size;
        initializeHash();
    }

    @Override
    public void add(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        // new Element
        if (hashElems.get(hashId) == nil) {
            //System.out.println("Dodaje nowy element: " + value + " [" + hashId + "]");
            hashElems.set(hashId, new Elem(value, nil));
            nElem++;
        } else {
            Elem oldElem = hashElems.get(hashId);
            while (oldElem.next != nil && !oldElem.value.equals(value)) {
                oldElem = oldElem.next;
            }
            if (oldElem.value.equals(value)) {
                //System.out.println("Element się powtarza: " + value + " [" + hashId + "]");
                oldElem.value = value;
            } else {
                //System.out.println("Rozszerzam listę: " + value + " [" + hashId + "]");
                oldElem.setNext(new Elem(value, nil));
                nElem++;
            }
        }
    }

    @Override
    public T get(T value) {
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
            nElem --;
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

    public int getNumberOfElements(){
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

    public void printAll() {
        System.out.println("HASH TABLE: ");
        System.out.println("Rozmiar: " + size + " Zapełnienie: " + countLoadFactor());
        System.out.println("");
        int counter = 0;
        for (Elem elem : hashElems) {
            System.out.println("[" + counter + "]");
            int elemCounter = 1;
            while (elem != nil) {
                System.out.println(elem.value);
                for (int i = 0; i < elemCounter; i++) {
                    System.out.print(" ");
                }
                elemCounter = elemCounter + 3;
                System.out.print("->");
                elem = elem.next;
                if (elem == nil) {
                    System.out.println("Null");
                }
            }
            counter++;
        }
    }
}