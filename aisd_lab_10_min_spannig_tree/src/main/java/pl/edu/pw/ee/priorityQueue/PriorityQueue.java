package pl.edu.pw.ee.priorityQueue;

import pl.edu.pw.ee.priorityQueue.services.PriorityQueueInterface;

public class PriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {
    private Heap<T> heap;

    public PriorityQueue() {
        heap = new Heap<>();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot insert null");
        }
        heap.put(item);
    }

    @Override
    public T getMax() {
        return heap.pop();
    }

    public int getSize(){
        return heap.getSize();
    }
}
