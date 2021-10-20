package pl.edu.pw.ee;

import java.util.Arrays;
import java.util.Random;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting{
    private Heap<Double> heap;

    public HeapSort(){
        heap = new Heap<>();
    }

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        for(double x : nums){
            heap.put(x);
        }

        for (int i = 0; i < nums.length; i++){
            nums[nums.length - i - 1] = heap.pop();
        }
        
    }

    public static void main (String[]argv){
        HeapSort heap = new HeapSort();
        Random random = new Random(1410);
        Random randoms = new Random(1410);
        double[] nums = new double[2];
        double[] expected = randoms.doubles(10).toArray();
        Arrays.fill(nums, 1.0);
        Arrays.fill(expected, 1.0);
        heap.sort(expected);
        for(int i = 0; i< nums.length; i++){
            System.out.println(nums[i]);
            System.out.println(expected[i]);
        }
    }
}
