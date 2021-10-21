package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {
    private Heap<Double> heap;

    public HeapSort() {
        heap = new Heap<>();
    }

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        for (double x : nums) {
            heap.put(x);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[nums.length - i - 1] = heap.pop();
        }

    }
}
