package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {

        if (nums == null) {
            throw new IllegalArgumentException();
        }
        if (nums.length != 1) {
            // First index
            int i = 0;
            // Second index
            int j = i + 1;
            while (i <= nums.length - 1) {
                double minValue = nums[i];
                int minIndex = i;
                while (j < nums.length) {
                    if (minValue > nums[j]) {
                        minValue = nums[j];
                        minIndex = j;
                    }
                    j++;
                }
                nums[minIndex] = nums[i];
                nums[i] = minValue;
                i++;
                j = i + 1;
            }
        }
    }
}
