package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting{
    public static void main(String[] argv){
        double []nums = {5, 3, 10, 6, 8, 9};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(nums);
        for (double x : nums){
            System.out.println(" " + x);
        }
    }

    @Override
    public void sort(double[] nums) {
        Heap<Double> heap = new Heap<>();
        for(double x : nums){
            heap.put(x);
        }

        for (int i = 0; i < nums.length; i++){
            nums[nums.length - i - 1] = heap.pop();
        }
        
    }
}
