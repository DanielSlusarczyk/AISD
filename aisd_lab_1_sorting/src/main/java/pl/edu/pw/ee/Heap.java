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
        items.add(size, item);
        size++;
        heapUp(size - 1);
    }

    @Override
    public T pop() {
        if(size == 0){
            throw new ArrayIndexOutOfBoundsException("Cannot pop from empty heap");
        }
        size--;
        Collections.swap(items, 0, size);
        heapDown(0);
        return items.get(size);
    }

    public void printHeap() {
        System.out.println("KOPIEC:");
        for(int i = 0; i < size; i++){
            try {
                System.out.println("ITEM: " + items.get(i) + "(" + i + "):");
                System.out.println("Parent: " + items.get(getIndexParent(i)) + "(" + getIndexParent(i) + ")");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Parent: brak");
            }
            try{
                System.out.println("Children: Left: " + items.get(getIndexLeftChildren(i)));
            } catch (IndexOutOfBoundsException e){
                System.out.println("Children: Left: brak");
            }
            try{
                System.out.println("Right: " + items.get(getIndexRightChildren(i)));
            } catch (IndexOutOfBoundsException e){
                System.out.println("Children: Right: brak");
            }
            System.out.println("");
        }
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void heapUp(int index) {

        if (hasParent(index) && items.get(index).compareTo(items.get(getIndexParent(index))) >= 1) {
            Collections.swap(items, index, getIndexParent(index));
            heapUp(getIndexParent(index));
        }
    }

    private void heapDown(int index) {
        if (getNumberOfChildren(index) > 0 && items.get(index).compareTo(items.get(getIndexGreaterChildren(index))) < 0) {
            int swapIndex = getIndexGreaterChildren(index);
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

    private int getIndexGreaterChildren(int index){
        if(getNumberOfChildren(index) == 1){
            return getIndexLeftChildren(index);
        }
        else if (getNumberOfChildren(index) == 2){
            if(items.get(getIndexLeftChildren(index)).compareTo(items.get(getIndexRightChildren(index))) > 0){
                return getIndexLeftChildren(index);
            }
            else{
                return getIndexRightChildren(index);
            }
        }
        return -1;
    }

    private int getIndexLeftChildren(int parentIndex) {
        return items.size() > parentIndex * 2 + 1 ? (parentIndex * 2 + 1) : -1;
    }

    private int getIndexRightChildren(int parentIndex) {
        return items.size() > parentIndex * 2 + 2 ? (parentIndex * 2 + 2) : -1;
    }

    private int getIndexParent(int index) {
        return index > 0 && index < size ? ((index - 1) / 2) : -1;
    }

}
