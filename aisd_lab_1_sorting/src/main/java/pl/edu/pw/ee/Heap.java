package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    private int size;
    private List<T> items;

    public Heap(){
        items= new ArrayList<>();
    }

    @Override
    public void put(T item) {
        items.add(item);
        size++;
        heapUp(item);
    }

    @Override
    public T pop() {
        size--;
        Collections.swap(items, 0, size);
        heapDown(items.get(0));
        return items.get(size);
    }

    public void printHeap() {
        System.out.println("KOPIEC:");
        for (T item : items) {
            try {
                System.out.println("ITEM: " + item + "(" + items.indexOf(item) + "):");
                System.out.println("Parent: " + getParent(item) + "(" + items.indexOf(getParent(item)) + ")");
                System.out.println("Children: Left: " + getLeftChildren(item) + " Right: " + getRightChildren(item));
                System.out.println("");
            } catch (IndexOutOfBoundsException | NullPointerException e) {

            }
        }
    }

    private void heapUp(T node) {
        if (hasParent(node) && node.compareTo(getParent(node)) >= 1) {
            Collections.swap(items, items.indexOf(node), items.indexOf(getParent(node)));
            heapUp(node);
        }
    }

    private void heapDown(T node) {
        if (numberOfChildren(node) > 0 && node.compareTo(getGreaterChildren(node)) < 0) {
            Collections.swap(items, items.indexOf(node), items.indexOf(getGreaterChildren(node)));
            heapDown(node);
        }
    }

    private boolean hasParent(T node) {
        int index = items.indexOf(node);
        return index > 0;
    }

    private int numberOfChildren(T node) {
        int index = items.indexOf(node);
        if ((index * 2) + 1 == size - 1)
            return 1;
        else if ((index * 2) + 1 < size)
            return 2;
        else
            return 0;
    }

    private T getGreaterChildren(T node){
        if(numberOfChildren(node) == 1){
            return getLeftChildren(node);
        }
        else if (numberOfChildren(node) == 2){
            if(getLeftChildren(node).compareTo(getRightChildren(node)) > 0){
                return getLeftChildren(node);
            }
            else{
                return getRightChildren(node);
            }
        }
        return null;
    }

    private T getLeftChildren(T parentNode) {
        int index = items.indexOf(parentNode);
        return items.size() > index * 2 + 1 ? items.get(index * 2 + 1) : null;
    }

    private T getRightChildren(T parentNode) {
        int index = items.indexOf(parentNode);
        return items.size() > index * 2 + 2 ? items.get(index * 2 + 2) : null;
    }

    private T getParent(T childrenNode) {
        int index = items.indexOf(childrenNode);
        return index > 0 ? items.get((index - 1) / 2) : null;
    }

}
