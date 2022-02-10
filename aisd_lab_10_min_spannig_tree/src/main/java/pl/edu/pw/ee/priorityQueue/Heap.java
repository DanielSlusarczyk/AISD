package pl.edu.pw.ee.priorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.pw.ee.priorityQueue.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {
    private int size;
    private List<T> items;

    public Heap() {
        items = new ArrayList<>();
    }

    @Override
    public void put(T item) {
        validateInput(item);
        items.add(size, item);
        size++;
        heapUp(size - 1);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Cannot pop from empty heap");
        }
        size--;
        Collections.swap(items, 0, size);
        heapDown(0);
        return items.get(size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private void heapUp(int index) {
        if (hasParent(index) && items.get(index).compareTo(items.get(getIndexOfParent(index))) >= 0) {
            Collections.swap(items, index, getIndexOfParent(index));
            heapUp(getIndexOfParent(index));
        }
    }

    private void heapDown(int index) {
        if (getNumberOfChildren(index) > 0
                && items.get(index).compareTo(items.get(getIndexOfGreaterChildren(index))) < 0) {
            int swapIndex = getIndexOfGreaterChildren(index);
            Collections.swap(items, index, swapIndex);
            heapDown(swapIndex);
        }
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private int getNumberOfChildren(int index) {
        if ((index * 2) + 1 == size - 1)
            return 1;
        else if ((index * 2) + 1 < size)
            return 2;
        else
            return 0;
    }

    private int getIndexOfGreaterChildren(int index) {
        if (getNumberOfChildren(index) == 1) {
            return getIndexOfLeftChildren(index);
        }

        if (items.get(getIndexOfLeftChildren(index)).compareTo(items.get(getIndexOfRightChildren(index))) > 0) {
            return getIndexOfLeftChildren(index);
        } else {
            return getIndexOfRightChildren(index);
        }
    }

    private int getIndexOfLeftChildren(int parentIndex) {
        return parentIndex * 2 + 1;
    }

    private int getIndexOfRightChildren(int parentIndex) {
        return parentIndex * 2 + 2;
    }

    private int getIndexOfParent(int index) {
        return ((index - 1) / 2);
    }

    private void validateInput(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot put null");
        }
    }
}
