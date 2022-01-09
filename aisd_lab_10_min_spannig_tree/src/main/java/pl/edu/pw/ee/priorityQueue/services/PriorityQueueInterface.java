package pl.edu.pw.ee.priorityQueue.services;

public interface PriorityQueueInterface <T extends Comparable<T>> {
    public boolean isEmpty();
    public void insert(T item);
    public T getMax();
}
