package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    private int size;
    private List<T> items;

    public Heap() {
        items = new ArrayList<>();
    }

    @Override
    public void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot put null");
        }
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

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapUp(int index) {
        if (hasParent(index) && items.get(index).compareTo(items.get(getIndexOfParent(index))) >= 1) {
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
        } else if (getNumberOfChildren(index) == 2) {
            if (items.get(getIndexOfLeftChildren(index)).compareTo(items.get(getIndexOfRightChildren(index))) > 0) {
                return getIndexOfLeftChildren(index);
            } else {
                return getIndexOfRightChildren(index);
            }
        }
        return -1;
    }

    private int getIndexOfLeftChildren(int parentIndex) {
        return items.size() > parentIndex * 2 + 1 ? (parentIndex * 2 + 1) : -1;
    }

    private int getIndexOfRightChildren(int parentIndex) {
        return items.size() > parentIndex * 2 + 2 ? (parentIndex * 2 + 2) : -1;
    }

    private int getIndexOfParent(int index) {
        return index > 0 && index < size ? ((index - 1) / 2) : -1;
    }
}
